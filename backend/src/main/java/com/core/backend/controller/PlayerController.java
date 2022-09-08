package com.core.backend.controller;

import com.core.backend.controller.dto.PlayerResponse;
import com.core.backend.service.PlayerService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/player")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping("")
    public ResponseEntity<List<PlayerResponse>> getAll() {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(playerService.getAll());
    }

}
