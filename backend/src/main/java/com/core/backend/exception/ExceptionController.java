package com.core.backend.exception;

import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(IsNotValidHeadCoachName.class)
    public ResponseEntity<ExceptionResponse> handleIsNotValidHeadCoachName(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponse("이미 존재하는 이름입니다."));
    }

    @ExceptionHandler(IsNotSamePassword.class)
    public ResponseEntity<ExceptionResponse> handleIsNotSamePassword(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponse("입력한 패스워드와 패스워드 확인이 다릅니다."));
    }

    @ExceptionHandler(NoValidHeadCoach.class)
    public ResponseEntity<ExceptionResponse> handleNoValidHeadCoach(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponse("해당 정보의 유저가 없습니다."));
    }

    @ExceptionHandler(PasswordNotMatch.class)
    public ResponseEntity<ExceptionResponse> handlePasswordNotMatch(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponse("비밀번호가 일치하지 않습니다."));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> allExceptionHandler(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponse(ex.getMessage()));
    }
}
