package com.lsm.boot_project01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO {
    private Long rno; // 고유번호
    private Long bno; //특정 게시물 번호 ( 현재 댓글이 특정 게시물의 댓글임을 알게 하기 위해 )
    private String replyText;
    private String commenter;
    private LocalDateTime regDate, modDate;

}
