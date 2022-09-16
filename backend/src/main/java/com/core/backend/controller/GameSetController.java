package com.core.backend.controller;

import com.core.backend.controller.dto.BanpickRequest;
import com.core.backend.controller.dto.BanpickResponse;
import com.core.backend.controller.dto.GameMatchResponse;
import com.core.backend.controller.dto.GameSetResponse;
import com.core.backend.domain.GameMatch;
import com.core.backend.domain.GameSet;
import com.core.backend.service.GameMatchService;
import com.core.backend.service.GameSetService;
import com.core.backend.service.LeagueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/set")
@RequiredArgsConstructor
public class GameSetController {

    private final GameSetService gameSetService;
    private final LeagueService leagueService;

    @GetMapping("/{id}")
    public ResponseEntity<GameSetResponse> getSetData(@PathVariable Long id){
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(gameSetService.getSetData(id));
    }

    @PostMapping("/banpick")
    public ResponseEntity<BanpickResponse> banpick(@RequestBody BanpickRequest banpickRequest){
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(gameSetService.banpick(banpickRequest));
    }

    @PostMapping("/play/{id}")
    public ResponseEntity<GameSetResponse> play(@PathVariable Long id){
        GameSet gameSet = gameSetService.play(id);
        if(gameSet.getGameMatch().isFinish()){
            leagueService.progressLeague(gameSet.getGameMatch());
        }
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(gameSetService.makeGameSetResponse(gameSet));
    }
}
