package com.core.backend.controller;

import com.core.backend.controller.dto.PlayerResponse;
import com.core.backend.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/player")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping("")
    public ResponseEntity<List<PlayerResponse>> getAll() {
        List<PlayerResponse> body = playerService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @GetMapping("/faker")
    public ResponseEntity<PlayerResponse> getFaker() {
        PlayerResponse body = playerService.getFaker();
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}
