package com.core.backend.service;

import com.core.backend.controller.dto.ChampionResponse;
import com.core.backend.domain.Champion;
import com.core.backend.domain.enums.Feature;
import com.core.backend.domain.enums.Position;
import com.core.backend.domain.repository.ChampionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ChampionService {

    private final ChampionRepository championRepository;

    public List<ChampionResponse> getAll(){
        List<Champion> championList = championRepository.findAll();
        return  championList.stream()
                .map(ChampionResponse::of)
                .collect(Collectors.toList());
    }
}
