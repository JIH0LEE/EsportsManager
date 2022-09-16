package com.core.backend.controller;

import com.core.backend.controller.dto.LeagueDetailedInfoResponse;
import com.core.backend.controller.dto.MessageResponse;
import com.core.backend.controller.dto.MyTeamRequest;
import com.core.backend.controller.dto.PersonalScheduleListRequest;
import com.core.backend.controller.dto.TeamRankResponse;
import com.core.backend.service.LeagueService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<MessageResponse> makeMyLeague(@RequestBody MyTeamRequest myTeamRequest) {

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(leagueService.makeMyLeague(myTeamRequest));
    }

    @PostMapping("/{id}")
    public ResponseEntity<MessageResponse> makeNewLeague(@PathVariable Long id) {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(leagueService.makeNewLeague(id));
    }

    @GetMapping("/league-info/{id}")
    public ResponseEntity<LeagueDetailedInfoResponse> getLeagueInfo(@PathVariable Long id) {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(leagueService.getLeagueDetailedInfo(id));
    }

    @GetMapping("/league-rank/{id}")
    public ResponseEntity<List<TeamRankResponse>> getLeagueRankingInfoByUser(
        @PathVariable Long id) {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(leagueService.getRankingInfoByUser(id));
    }

    @PostMapping("/league-process")
    public ResponseEntity<MessageResponse> progressLeague(
        @RequestBody PersonalScheduleListRequest request) {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(leagueService.progressLeague(request));
    }

}
