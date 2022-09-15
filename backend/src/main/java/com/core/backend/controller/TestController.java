package com.core.backend.controller;

import com.core.backend.domain.GameMatch;
import com.core.backend.service.GameSetService;
import com.core.backend.service.LeagueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController {

    private final GameSetService gameSetService;
    private final LeagueService leagueService;


    @GetMapping("{id}")
    public void test(@PathVariable Long id) {
        GameMatch gameMatch = gameSetService.play(id);
        if(gameMatch.isFinish()){
            leagueService.progressLeague(gameMatch);
        }
    }

}
