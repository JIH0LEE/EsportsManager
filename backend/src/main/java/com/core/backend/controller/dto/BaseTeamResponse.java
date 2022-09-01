package com.core.backend.controller.dto;

import com.core.backend.domain.BaseTeam;
import com.core.backend.domain.Champion;
import com.core.backend.domain.enums.Feature;
import com.core.backend.domain.enums.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@AllArgsConstructor
public class BaseTeamResponse {
    private Long id;

    private String name;

    private String logo;

    public static BaseTeamResponse of(BaseTeam baseTeam){
        return new BaseTeamResponse(
                baseTeam.getId(),
                baseTeam.getName(),
                baseTeam.getLogo());
    }
}
