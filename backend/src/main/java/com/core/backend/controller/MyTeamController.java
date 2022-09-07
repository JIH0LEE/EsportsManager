package com.core.backend.controller;

import com.core.backend.controller.dto.MessageResponse;
import com.core.backend.controller.dto.MyTeamRequest;
import com.core.backend.controller.dto.MyTeamResponse;
import com.core.backend.service.LeagueService;
import com.core.backend.service.MyTeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/my-team")
@RequiredArgsConstructor
public class MyTeamController {

    private final MyTeamService myTeamService;

    @GetMapping("/{id}")
    public ResponseEntity<MyTeamResponse> getLeagueInfo(@PathVariable Long id){
        MyTeamResponse body = myTeamService.findTeamByUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

}
