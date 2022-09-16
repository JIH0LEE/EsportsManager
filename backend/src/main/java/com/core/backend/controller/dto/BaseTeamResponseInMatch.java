package com.core.backend.controller.dto;

import com.core.backend.domain.BaseTeam;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseTeamResponseInMatch {

    private Long Id;

    private String name;

    private PlayerResponse top;

    private PlayerResponse jng;

    private PlayerResponse mid;

    private PlayerResponse adc;

    private PlayerResponse sup;

    public static BaseTeamResponseInMatch of(BaseTeam baseTeam) {
        return new BaseTeamResponseInMatch(
            baseTeam.getId(),
            baseTeam.getName(),
            PlayerResponse.of(baseTeam.findPlayerByPosition("TOP")),
            PlayerResponse.of(baseTeam.findPlayerByPosition("JUNGLE")),
            PlayerResponse.of(baseTeam.findPlayerByPosition("MIDDLE")),
            PlayerResponse.of(baseTeam.findPlayerByPosition("ADC")),
            PlayerResponse.of(baseTeam.findPlayerByPosition("SUPPORT"))
        );
    }
}
