package com.lsm.boot_project01.repository;

import com.lsm.boot_project01.domain.Board;
import com.lsm.boot_project01.dto.BoardListReplyCountDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
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

    @Test
    public void testPaging(){
        // 1-page order by bno desc
        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());
        Page<Board> result = repository.findAll(pageable);
        log.info("---------- " + "result : " + result + "---------- ");
    }

    @Test
    public void testListing(){
        Pageable pageable = PageRequest.of(0,10,Sort.by("bno").descending());
        Page<Board> result = repository.findAll(pageable);
        log.info("total count : " + result.getTotalElements());
        log.info("total pages : " + result.getTotalPages());
        log.info("total numbers : " + result.getNumber());
        log.info("total size : " + result.getSize());

        List<Board> boardList = result.getContent();

        boardList.forEach(log::info);
    }

    @Test
    public void testSearch1(){
        //2-page order by bno desc
        Pageable pageable = PageRequest.of(1,10,Sort.by("bno").descending());
        repository.search1(pageable);
    }

    @Test
    public void testSearchAll(){
        String[] types = {"t", "c", "w"};
        String keyword = "2";

        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());
        Page<Board> result = repository.searchAll(types, keyword, pageable);
        log.info("-----------" + " result : "+ result + "-----------" ); // result : null
    }

    @Test
    public void testSearchALl2(){
        String [] types = {"t", "c", "w"};
        String keyword = "2";

        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());

        Page<Board> result = repository.searchAll(types, keyword, pageable);

        log.info("-----------" + " getTotalPages : "+ result.getTotalPages() + "-----------" ); //total page
        log.info("-----------" + " getSize : "+ result.getSize() + "-----------" ); //page size
        log.info("-----------" + " getNumber : "+ result.getNumber() + "-----------" ); //page number
        log.info("-----------" + " prev / next : "+ result.hasPrevious() + ":" +result.hasNext() + "-----------" ); // prev next

        result.getContent().forEach(log::info); // content
    }

    @Test
    public void testSearchReplyCount(){
        String[] types = {"t", "c", "w"};
        String keyword = "1";
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<BoardListReplyCountDTO> result = repository.searchWithReplyCount(types, keyword, pageable);

        log.info(result.getTotalPages()); // total page
        log.info(result.getSize()); // page size
        log.info(result.getNumber()); // prev next
        log.info(result.hasPrevious() + " : " + result.hasNext());

        result.getContent().forEach(log::info);
    }
}
