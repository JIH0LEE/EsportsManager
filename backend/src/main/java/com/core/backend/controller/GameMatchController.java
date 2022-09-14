package com.core.backend.controller;

import com.core.backend.controller.dto.GameMatchResponse;
import com.core.backend.service.GameMatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/match")
@RequiredArgsConstructor
public class GameMatchController {

    private final GameMatchService gameMatchService;

    @GetMapping("/{id}")
    public ResponseEntity<GameMatchResponse> getMatchData(@PathVariable Long id){
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(gameMatchService.getMatchData(id));
    }
}
