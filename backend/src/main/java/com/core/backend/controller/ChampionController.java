package com.core.backend.controller;

import com.core.backend.controller.dto.HeadCoachRequest;
import com.core.backend.controller.dto.HeadCoachResponse;
import com.core.backend.service.ChampionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/champion")
@RequiredArgsConstructor
public class ChampionController {

    private final ChampionService championService;

    @PostMapping("/add")
    public void create(){
        championService.create();
    }
}
