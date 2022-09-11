package com.core.backend.controller;

import com.core.backend.controller.dto.*;
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

    @PostMapping("/sponsor")
    public ResponseEntity<MessageResponse> contactSponsor(@RequestParam("user") Long id
                                                                 ,@RequestParam("sponsor") Long sponsorId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(myTeamService.contractSponsor(id,sponsorId));
    }

    @PostMapping("/enterprise")
    public ResponseEntity<MessageResponse> startEnterprise(@RequestParam("user") Long id
                                                                ,@RequestParam("enterprise") Long enterpriseId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(myTeamService.startEnterprise(id,enterpriseId));
    }

    @PostMapping("/schedule")
    public ResponseEntity<MessageResponse> applySchedule(@RequestBody PersonalScheduleListRequest request){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(myTeamService.applySchedule(request));
    }
}
