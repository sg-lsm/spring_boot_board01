package com.lsm.boot_project01.service;

import com.lsm.boot_project01.domain.Board;
import com.lsm.boot_project01.dto.BoardDTO;
import com.lsm.boot_project01.dto.PageRequestDTO;
import com.lsm.boot_project01.dto.PageResponseDTO;
import com.lsm.boot_project01.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public PageResponseDTO<BoardDTO> list(PageRequestDTO requestDTO) {
        String[] types = requestDTO.getTypes();
        String keyword = requestDTO.getKeyword();
        Pageable pageable = requestDTO.getPageable("bno");

        Page<Board> result = repository.searchAll(types, keyword, pageable);

        // Board type Page -> content -> 객체변환매핑 -> list
        List<BoardDTO> dtoList = result.getContent().stream().map(board->modelMapper.map(board, BoardDTO.class)).collect(Collectors.toList());

        // PageResponseDTO(PageRequestDTO requestDTO, List<E> dtoList, int total){}
        return PageResponseDTO.<BoardDTO>withAll()
                .requestDTO(requestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }
}
