package com.lsm.boot_project01.controller;

import com.lsm.boot_project01.dto.ReplyDTO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/replies")
@Log4j2
// https://velog.io/@ckdgml1227/Swagger-UI
public class ReplyController {
    @ApiOperation(value = "Replies POST", notes = "POST 방식의 댓글 등록")
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Long>> register(@RequestBody ReplyDTO replyDTO){
        log.info(replyDTO);
        Map<String, Long> resultMap = Map.of("rno", 111L);
        return ResponseEntity.ok(resultMap);
    }
}
