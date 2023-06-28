package com.lsm.boot_project01.repository;

import com.lsm.boot_project01.domain.Board;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {
    @Autowired
    private BoardRepository repository;

    @Test
    public void testInsert(){
        IntStream.rangeClosed(1, 100).forEach(v->{
            Board board = Board.builder()
                    .title("title..." + v)
                    .content("content..."+ v)
                    .writer("user"+ (v % 10))
                    .build();

            Board result = repository.save(board);
            log.info("BNO : " + result.getBno());
        });
    }

    @Test
    public void testSelect(){
        Long bno = 100L;

        Optional<Board> result = repository.findById(bno);
        Board board = result.orElseThrow();

        log.info(board);
    }

    @Test
    public void testUpdate(){
        Long tno = 100L;
        Optional<Board> result = repository.findById(tno);
        Board board = result.orElseThrow();
        board.change("update... title " + tno, "update content " + tno);
        repository.save(board);
        log.info(board);
    }

    @Test
    public void testDelete(){
        Long tno = 1L;
        repository.deleteById(tno);
    }
}
