package com.core.backend.controller.dto;

import com.core.backend.domain.LeagueTeam;
import com.core.backend.domain.MyTeam;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TeamRankResponse {

    private String teamName;

    private Integer matchWin;

    private Integer matchLose;

    private Integer winPoint;

    public static TeamRankResponse of(LeagueTeam leagueTeam) {
        return new TeamRankResponse(
            leagueTeam.getBaseTeam().getName(),
            leagueTeam.getMatchWin(),
            leagueTeam.getMatchLose(),
            leagueTeam.getWinPoint()
        );
    }

    public static TeamRankResponse of(MyTeam myTeam) {
        return new TeamRankResponse(
            myTeam.getName(),
            myTeam.getMatchWin(),
            myTeam.getMatchLose(),
            myTeam.getWinPoint()
        );
    }
}
