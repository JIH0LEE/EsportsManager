package com.core.backend.controller;

import com.core.backend.controller.dto.MyTeamRequest;
import com.core.backend.service.LeagueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/league")
@RequiredArgsConstructor
public class LeagueController {

    private final LeagueService leagueService;

    @PostMapping("")
    public void make(@RequestBody MyTeamRequest myTeamRequest) {
        leagueService.makeMyLeague(myTeamRequest);
    }

}
