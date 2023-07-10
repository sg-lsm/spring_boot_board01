package com.lsm.boot_project01.service;

import com.lsm.boot_project01.dto.BoardDTO;
import com.lsm.boot_project01.dto.PageRequestDTO;
import com.lsm.boot_project01.dto.PageResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class BoardServiceTests {
    @Autowired
    private BoardService service;

    @Test
    public void testRegister(){
        log.info(service.getClass().getName()); // service.BoardServiceImpl$$EnhancerBySpringCGLIB$$d89560a (boardService 변수가 가르키는 객체의 클래스명인 BoardServiceImpl이 나오지 않고 그를 감싸 만든 클래스 정보가 출력되었다.)
        BoardDTO boardDTO = BoardDTO.builder()
                .title("sample test.01")
                .content("sample content01")
                .writer("sample user01")
                .build();
        Long bno = service.register(boardDTO);
        log.info("bno : " + bno);
    }

    @Test
    public void testReadOne(){
        BoardDTO readOne = service.readOne((long)2);
        String result = readOne.getTitle();
        log.info("readOne : "+result);
    }

    @Test
    public void testModify(){
        BoardDTO boardDTO = BoardDTO.builder()
                .title("updated..101")
                .bno(100L)
                .content("updated user 101")
                .build();
        service.modify(boardDTO);
        BoardDTO readOne = service.readOne((long)100);
        log.info("modify 101 : " + readOne);
    }

    @Test
    public void testRemove(){
        log.info(service.readOne(100L));
        service.remove(100L);
//        log.info(service.readOne(100L));
    }

    @Test
    public void testList(){
        PageRequestDTO requestDTO = PageRequestDTO.builder()
                .type("tcw")
                .keyword("1")
                .page(1)
                .size(10)
                .build();
        PageResponseDTO<BoardDTO> responseDTO = service.list(requestDTO);
        log.info(responseDTO);
    }
}
