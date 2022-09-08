package com.core.backend.controller;

import com.core.backend.controller.dto.MyTeamResponse;
import com.core.backend.service.MyTeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/my-team")
@RequiredArgsConstructor
public class MyTeamController {

    private final MyTeamService myTeamService;

    @GetMapping("/{id}")
    public ResponseEntity<MyTeamResponse> getLeagueInfo(@PathVariable Long id) {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(myTeamService.findTeamByUser(id));
    }

}
