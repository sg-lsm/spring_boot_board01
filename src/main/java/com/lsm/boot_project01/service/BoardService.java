package com.lsm.boot_project01.service;

import com.lsm.boot_project01.dto.BoardDTO;

public interface BoardService {
    Long register(BoardDTO boardDTO);
    BoardDTO readOne(Long bno);
    void modify(BoardDTO boardDTO);
}
