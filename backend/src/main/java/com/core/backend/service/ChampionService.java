package com.core.backend.service;

import com.core.backend.domain.Champion;
import com.core.backend.domain.enums.Feature;
import com.core.backend.domain.enums.Position;
import com.core.backend.domain.repository.ChampionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ChampionService {

    private final ChampionRepository championRepository;

    public void create(){
        Champion champion = Champion.builder()
                .englishName("test")
                .koreanName("테스트")
                .feature(Feature.LANE)
                .image("test.jpg")
                .position(Position.TOP)
                .tier(1)
                .build();
        championRepository.save(champion);
    }
}
