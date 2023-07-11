package com.lsm.boot_project01.controller;

import com.lsm.boot_project01.dto.BoardDTO;
import com.lsm.boot_project01.dto.PageRequestDTO;
import com.lsm.boot_project01.dto.PageResponseDTO;
import com.lsm.boot_project01.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor // final initialize
public class BoardController {
    private final BoardService service;

    @GetMapping("/list")
    public void list(PageRequestDTO requestDTO, Model model){
        PageResponseDTO<BoardDTO> responseDTO = service.list(requestDTO);
        log.info(responseDTO);
        model.addAttribute("responseDTO", responseDTO);
    }
}
