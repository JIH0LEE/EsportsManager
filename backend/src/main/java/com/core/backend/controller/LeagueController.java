package com.core.backend.controller;

import com.core.backend.controller.dto.MessageResponse;
import com.core.backend.controller.dto.MyTeamRequest;
import com.core.backend.controller.dto.MyTeamResponse;
import com.core.backend.service.LeagueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/league")
@RequiredArgsConstructor
public class LeagueController {

    private final LeagueService leagueService;

    @PostMapping("")
    public ResponseEntity<MessageResponse> make(@RequestBody MyTeamRequest myTeamRequest) {
        leagueService.makeMyLeague(myTeamRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true,"리그가 생성되었습니다."));
    }

}
