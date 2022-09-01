package com.core.backend.controller;

import com.core.backend.controller.dto.ChampionResponse;
import com.core.backend.controller.dto.HeadCoachRequest;
import com.core.backend.controller.dto.HeadCoachResponse;
import com.core.backend.service.ChampionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/champion")
@RequiredArgsConstructor
public class ChampionController {

    private final ChampionService championService;

    @GetMapping("")
    public ResponseEntity<List<ChampionResponse>> getAll(){
        List<ChampionResponse> body = championService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}
