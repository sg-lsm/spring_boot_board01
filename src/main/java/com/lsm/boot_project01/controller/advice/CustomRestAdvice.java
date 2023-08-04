package com.lsm.boot_project01.controller.advice;
// REST 방식의 컨트롤러는 대체적으로 Ajax와 같이 흐름을 직관적으로 알 수 없는 방식으로 서버를 호출하고 결과를 전송하므로 에러가 발생하면 어디에서 어떤 에러가 발생했는지 알기 어렵다. 이런 이유로 @Valid 과정에서 문제가 발생하면 처리할 수 있도록 @RestControllerAdvice를 만들어 처리하려고 한다.

import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.validation.BindException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Log4j2
public class CustomRestAdvice {
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ResponseEntity<Map<String, String>> handleBindException(BindException e){
        //log.info(e)
        log.error(e);

        Map<String, String> errorMap = new HashMap<>();

        if(e.hasErrors()){
            BindingResult bindingResult = e.getBindingResult();
            bindingResult.getFieldErrors().forEach(error -> {
                errorMap.put(error.getField(), error.getCode());
            });
        }
        return ResponseEntity.badRequest().body(errorMap);
    }

    // 존재하지 않는 게시물 번호 (bno)로 검색할 경우 발생하는 에러처리 ->
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ResponseEntity<Map<String,String>> handleFKException(Exception e){
        log.error(e);
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Error Time",""+System.currentTimeMillis());
        errorMap.put("Message", "constrain failed");
        return ResponseEntity.badRequest().body(errorMap);
    }

    @ExceptionHandler({NoSuchFieldError.class, EmptyResultDataAccessException.class})
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ResponseEntity<Map<String, String>> handleNoSuchElements(Exception e){
        log.error(e);

        Map<String, String> errorMap = new HashMap<>();

        errorMap.put("time", "" + System.currentTimeMillis());
        errorMap.put("msg", "No Such Element Exception");
        return ResponseEntity.badRequest().body(errorMap);
    }
}
