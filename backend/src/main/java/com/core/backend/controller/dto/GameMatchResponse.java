package com.core.backend.controller.dto;

import com.core.backend.domain.GameMatch;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GameMatchResponse {

    private Long id;

    private MyTeamResponseInMatch myTeam;

    private BaseTeamResponseInMatch oppositeTeam;

    private boolean isFinish;

    private Integer gameSetCount;

    private Integer gameScore;

    private boolean isBlue;

    public static GameMatchResponse of(GameMatch gameMatch) {
        return new GameMatchResponse(
            gameMatch.getId(),
            MyTeamResponseInMatch.of(gameMatch.getMyTeam()),
            BaseTeamResponseInMatch.of(gameMatch.getOppositeTeam()),
            gameMatch.isFinish(),
            gameMatch.getGameSetCount(),
            gameMatch.getGameScore(),
            gameMatch.isBlue()
        );
    }

}
