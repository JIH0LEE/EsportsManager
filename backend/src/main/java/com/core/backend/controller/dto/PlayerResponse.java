package com.core.backend.controller.dto;

import com.core.backend.domain.Player;
import com.core.backend.domain.enums.Grade;
import com.core.backend.domain.enums.Position;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlayerResponse {

    private Long id;

    private String nickName;

    private String realName;

    @Enumerated(EnumType.STRING)
    private Position position;

    private Integer laneStatus;

    private Integer fightStatus;

    private Integer operationStatus;

    private Integer roamingStatus;

    private Integer gankingStatus;

    private Integer junglingStatus;

    private Grade grade;

    private Integer price;

    private String image;

    public static PlayerResponse of(Player player) {
        return new PlayerResponse(
            player.getId(),
            player.getNickName(),
            player.getRealName(),
            player.getPosition(),
            player.getLaneStatus(),
            player.getFightStatus(),
            player.getOperationStatus(),
            player.getRoamingStatus(),
            player.getGankingStatus(),
            player.getJunglingStatus(),
            player.getGrade(),
            player.getPrice(),
            player.getImage());

    }
}
