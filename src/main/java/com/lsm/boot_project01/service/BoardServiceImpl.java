package com.lsm.boot_project01.service;

import com.lsm.boot_project01.domain.Board;
import com.lsm.boot_project01.dto.BoardDTO;
import com.lsm.boot_project01.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional // spring이 해당 객체를 감싸는 별도의 클래스를 생성
public class BoardServiceImpl implements BoardService{
    private final ModelMapper modelMapper;
    private final BoardRepository repository;

    @Override
    public Long register(BoardDTO boardDTO){
        Board board = modelMapper.map(boardDTO, Board.class);
        Long bno = repository.save(board).getBno();
        return bno;
    }

    @Override
    public BoardDTO readOne(Long bno){
        Optional<Board> result = repository.findById(bno);
        Board board = result.orElseThrow();
        BoardDTO boardDTO = modelMapper.map(board, BoardDTO.class);
        return boardDTO;
    }

    @Override
    public void modify(BoardDTO boardDTO) {
        Optional<Board> result = repository.findById(boardDTO.getBno());
        Board board = result.orElseThrow();
        board.change(boardDTO.getTitle(), boardDTO.getContent());
        repository.save(board);
    }

    @Override
    public void remove(Long bno) {
        repository.deleteById(bno);
    }
}
