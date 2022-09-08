package com.core.backend.controller.dto;

import com.core.backend.domain.Champion;
import com.core.backend.domain.enums.Position;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChampionResponse {

    private Long id;

    private String koreanName;

    private String englishName;

    private String image;

    @Enumerated(EnumType.STRING)
    private Position position;

    private Integer tier;

    public static ChampionResponse of(Champion champion) {
        return new ChampionResponse(
            champion.getId(),
            champion.getKoreanName(),
            champion.getEnglishName(),
            champion.getImage(),
            champion.getPosition(),
            champion.getTier());
    }
}
