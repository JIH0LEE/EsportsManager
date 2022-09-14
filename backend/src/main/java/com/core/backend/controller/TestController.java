package com.core.backend.controller;

import com.core.backend.service.GameSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
