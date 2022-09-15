package com.core.backend.controller;

import com.core.backend.controller.dto.BanpickRequest;
import com.core.backend.controller.dto.BanpickResponse;
import com.core.backend.controller.dto.GameMatchResponse;
import com.core.backend.service.GameMatchService;
import com.core.backend.service.GameSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/set")
@RequiredArgsConstructor
public class GameSetController {

    private final GameSetService gameSetService;

    @PostMapping("/banpick")
    public ResponseEntity<BanpickResponse> getMatchData(@RequestBody BanpickRequest banpickRequest){
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(gameSetService.banpick(banpickRequest));
    }
}
