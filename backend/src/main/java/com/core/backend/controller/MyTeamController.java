package com.core.backend.controller;

import com.core.backend.controller.dto.MyTeamRequest;
import com.core.backend.controller.dto.PlayerResponse;
import com.core.backend.service.MyTeamService;
import com.core.backend.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/my-team")
@RequiredArgsConstructor
public class MyTeamController {

    private final MyTeamService myTeamService;

    @PostMapping("")
    public void make(@RequestBody MyTeamRequest myTeamRequest) {
        myTeamService.makeMyTeam(myTeamRequest);
    }

}
