package com.lsm.boot_project01.service;

import com.lsm.boot_project01.dto.ReplyDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class ReplyServiceTests {
    @Autowired
    private ReplyService service;

    @Test
    public void registerTest(){
        ReplyDTO replyDTO = ReplyDTO.builder()
                .replyText("replyDTO test")
                .commenter("commenter1")
                .bno(101L)
                .build();
        log.info(service.register(replyDTO));
    }
}
