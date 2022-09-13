package com.core.backend.controller;

import com.core.backend.controller.dto.PlayerResponse;
import com.core.backend.service.GameSetService;
import com.core.backend.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController {

    private final GameSetService gameSetService;

    @GetMapping("")
    public void test() {
        gameSetService.play();
    }

}
