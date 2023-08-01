package com.lsm.boot_project01.service;

import com.lsm.boot_project01.dto.PageRequestDTO;
import com.lsm.boot_project01.dto.PageResponseDTO;
import com.lsm.boot_project01.dto.ReplyDTO;

public interface ReplyService {
    Long register(ReplyDTO replyDTO);
    ReplyDTO read(Long rno);
    void modify(ReplyDTO replyDTO);
    void remove(Long rno);
    PageResponseDTO<ReplyDTO>  getListOfBoard(Long bno, PageRequestDTO pageRequestDTO);
}
