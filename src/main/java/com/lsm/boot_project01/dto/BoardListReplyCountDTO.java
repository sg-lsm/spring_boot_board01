package com.lsm.boot_project01.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardListReplyCountDTO {
    private Long bno;
    private String title;
    private String writer;
    private LocalDateTime localDateTime;
    private Long replyCount;
}
