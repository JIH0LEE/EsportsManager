package com.core.backend.controller;

import com.core.backend.controller.dto.BaseTeamResponse;
import com.core.backend.service.BaseTeamService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/base-team")
@RequiredArgsConstructor
public class BaseTeamController {

    private final BaseTeamService baseTeamService;

    @GetMapping("")
    public ResponseEntity<List<BaseTeamResponse>> getAll() {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(baseTeamService.getAll());
    }
}
