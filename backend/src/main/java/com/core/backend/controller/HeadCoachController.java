package com.core.backend.controller;

import com.core.backend.controller.dto.HeadCoachRequest;
import com.core.backend.controller.dto.HeadCoachResponse;
import com.core.backend.service.HeadCoachService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/head-coach")
@RequiredArgsConstructor
public class HeadCoachController {

    private final HeadCoachService headCoachService;

    @PostMapping("/register")
    public ResponseEntity<HeadCoachResponse> createHeadCoach(@RequestBody HeadCoachRequest headCoachRequest){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(headCoachService.createHeadCoach(headCoachRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<HeadCoachResponse> login(@RequestBody HeadCoachRequest headCoachRequest){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(headCoachService.login(headCoachRequest));
    }
}
