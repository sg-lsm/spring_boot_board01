package com.lsm.boot_project01.controller;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@Log4j2
public class SampleController {
    @Getter
    class SampleDTO{
        private String p1,p2,p3;
    }
    @GetMapping("/hello")
    public void hello(Model model){
        log.info("---- hello side ----");
        model.addAttribute("msg", "hello world!");
    }
    @GetMapping("/ex/ex01")
    public void ex01(Model model){
        List<String> list = Arrays.asList("AAA","BBB","CCC","DDD");
        log.info(list);
        model.addAttribute("list", list);
    }
    @GetMapping("/ex/ex02")
    public void ex02(Model model){
        log.info("----- ex02 side -----");
        List<String> strList = IntStream.range(0,10).mapToObj(v -> "DATA"+v)
                .collect(Collectors.toList());
        model.addAttribute("list", strList);

        Map<String, String> map = new HashMap<>();
        map.put("A", "AAAA");
        map.put("B", "BBBB");

        model.addAttribute("map", map);

        SampleDTO sampleDTO = new SampleDTO();
        sampleDTO.p1 = "Value -- p1";
        sampleDTO.p2 = "Value -- p2";
        sampleDTO.p3 = "Value -- p3";

        model.addAttribute("dto", sampleDTO);
    }

    @GetMapping("ex/ex03")
    public void ex03(Model model){
        model.addAttribute("arr", new String[]{"DDD", "EEE", "FFF"});
    }
}
