package com.lsm.boot_project01.controller;

import com.lsm.boot_project01.dto.BoardDTO;
import com.lsm.boot_project01.dto.PageRequestDTO;
import com.lsm.boot_project01.dto.PageResponseDTO;
import com.lsm.boot_project01.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

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

    @GetMapping("/register")
    public void registerGet(){}

    @PostMapping("/register")
    public String registerPost(@Valid BoardDTO boardDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        log.info("--- board POST register ---");

        if(bindingResult.hasErrors()){
            log.info("--- has Error --- ");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "redirect:/board/register";
        }

        log.info(boardDTO);

        Long bno = service.register(boardDTO);

        redirectAttributes.addFlashAttribute("result", bno);

        return "redirect:/board/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(Long bno, PageRequestDTO pageRequestDTO, Model model){
        BoardDTO boardDTO = service.readOne(bno);
        log.info(boardDTO);
        model.addAttribute("dto", boardDTO);
    }
    @PostMapping("/modify")
    public String modify(PageRequestDTO pageRequestDTO,
                       @Valid BoardDTO boardDTO,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes){

        log.info("board modify post ----- " + boardDTO);

        if(bindingResult.hasErrors()){
            log.info("error occoured : \"/modify");
            String link = pageRequestDTO.getLink();
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("bno", boardDTO.getBno());
            return "redirect:/board/read";
        }
        service.modify(boardDTO);
        redirectAttributes.addFlashAttribute("result", "modified");
        redirectAttributes.addAttribute("bno", boardDTO.getBno());
        return "redirect:/board/read";
    }

    @PostMapping("/remove")
    public String remove(Long bno, RedirectAttributes redirectAttributes){
        log.info("remove post ----- " + bno);
        service.remove(bno);
        redirectAttributes.addFlashAttribute("result", "removed");
        return "redirect:/board/list";
    }
}
