package com.core.backend.controller;

import com.core.backend.controller.dto.MessageResponse;
import com.core.backend.controller.dto.MyTeamRequest;
import com.core.backend.controller.dto.MyTeamResponse;
import com.core.backend.service.LeagueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/league")
@RequiredArgsConstructor
public class LeagueController {

    private final LeagueService leagueService;

    @PostMapping("")
    public ResponseEntity<MessageResponse> makeMyLeague(@RequestBody MyTeamRequest myTeamRequest) {
        leagueService.makeMyLeague(myTeamRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new MessageResponse(true,"리그가 생성되었습니다."));
    }

    @GetMapping("/league-info/{id}")
    public ResponseEntity<MessageResponse> getLeagueInfo(@PathVariable Long id){
        if(leagueService.getLeagueInfo(id).isPresent()){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new MessageResponse(true,"이미 생성된 리그가 있습니다."));
        }
        else{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new MessageResponse(false,"생성된 리그가 없습니다."));
        }
    }

}
