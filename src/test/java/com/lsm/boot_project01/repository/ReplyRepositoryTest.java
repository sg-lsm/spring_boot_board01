package com.lsm.boot_project01.repository;

import com.lsm.boot_project01.domain.Board;
import com.lsm.boot_project01.domain.Reply;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTest {
    @Autowired
    private ReplyRepository repository;

    @Test
    public void testInsert(){
        Long bno = 101L;

        Board board = Board.builder().bno(bno).build();
        Reply reply = Reply.builder()
                .board(board)
                .replyText("댓글 테스트")
                .commenter("코멘터01")
                .build();

        repository.save(reply);
    }

    @Test
    public void testBoardReplies(){
        Long bno = 101L;

        Pageable pageable = PageRequest.of(0,10, Sort.by("rno").descending());

        Page<Reply> result = repository.listOfBoard(bno, pageable);

        result.getContent().forEach(r -> {
            log.info(r);
        });
    }
}
