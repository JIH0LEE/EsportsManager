package com.core.backend.controller;

import com.core.backend.controller.dto.*;
import com.core.backend.service.MyTeamService;
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

    @PostMapping("/change-entry")
    public ResponseEntity<MessageResponse> changeEntry(@RequestBody ChangeEntryRequest changeEntryRequest){
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(myTeamService.changeEntry(changeEntryRequest));
    }

    @GetMapping("/sponsor/{id}")
    public ResponseEntity<TeamSponsorResponse> getSponsors(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(myTeamService.getSponsors(id));
    }

    @GetMapping("/enterprise/{id}")
    public ResponseEntity<TeamEnterpriseResponse> getEnterprises(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(myTeamService.getEnterprises(id));
    }
}
