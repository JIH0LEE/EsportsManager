package com.core.backend.controller.dto;

import com.core.backend.domain.GameSet;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GameSetResponse {

    private Long id;

    private List<String> champList;

    private List<Integer> towerList;

    private List<Integer> goldList;

    private Integer blueDragonCount;

    private Integer redDragonCount;

    private Integer blueBaron;

    private Integer redBaron;

    private boolean isBlue;

    private String gameLog;

    private Integer curTime;

    private GameMatchResponse gameMatch;

    private boolean isFinished;

    public static GameSetResponse of(GameSet gameSet, List<String> championList) {
        return new GameSetResponse(
            gameSet.getId(),
            championList,
            gameSet.getTowerList(),
            gameSet.getGoldList(),
            gameSet.getBlueDragonCount(),
            gameSet.getRedDragonCount(),
            gameSet.getBlueBaron(),
            gameSet.getRedBaron(),
            gameSet.isBlue(),
            gameSet.getGameLog(),
            gameSet.getCurTime(),
            GameMatchResponse.of(gameSet.getGameMatch()),
            gameSet.isFinished()
        );
    }

}
