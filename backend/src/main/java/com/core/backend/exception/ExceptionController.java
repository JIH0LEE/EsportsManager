package com.core.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(IsNotValidHeadCoachName.class)
    public ResponseEntity<ExceptionResponse> handleIsNotValidHeadCoachName(Exception ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponse("이미 존재하는 이름입니다."));
    }

    @ExceptionHandler(IsNotSamePassword.class)
    public ResponseEntity<ExceptionResponse> handleIsNotSamePassword(Exception ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponse("입력한 패스워드와 패스워드 확인이 다릅니다."));
    }
}
