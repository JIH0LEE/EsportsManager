package com.core.backend.controller.dto;

import com.core.backend.domain.BaseTeam;
import com.core.backend.domain.Champion;
import com.core.backend.domain.enums.Feature;
import com.core.backend.domain.enums.Position;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class BaseTeamResponse {
    private Long id;

    private String name;

    private String logo;

    @JsonProperty("players")
    private List<PlayerResponse> playerResponseList;

    public static BaseTeamResponse of(BaseTeam baseTeam){
        return new BaseTeamResponse(
                baseTeam.getId(),
                baseTeam.getName(),
                baseTeam.getLogo(),
                baseTeam.getPlayers().stream().map(PlayerResponse::of).collect(Collectors.toList()));
    }
}
