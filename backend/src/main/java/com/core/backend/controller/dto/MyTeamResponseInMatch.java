package com.core.backend.controller.dto;

import com.core.backend.domain.BaseTeam;
import com.core.backend.domain.MyPlayer;
import com.core.backend.domain.MyTeam;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MyTeamResponseInMatch {

    private Long id;

    private String name;

    private PlayerResponse top;

    private PlayerResponse jng;

    private PlayerResponse mid;

    private PlayerResponse adc;

    private PlayerResponse sup;

    public static MyTeamResponseInMatch of(MyTeam myTeam) {
        MyTeamResponse myTeamResponse = MyTeamResponse.of(myTeam);
        return new MyTeamResponseInMatch(
                myTeam.getId(),
                myTeam.getName(),
                myTeamResponse.getTop().getPlayerResponse(),
                myTeamResponse.getJng().getPlayerResponse(),
                myTeamResponse.getMid().getPlayerResponse(),
                myTeamResponse.getAdc().getPlayerResponse(),
                myTeamResponse.getSup().getPlayerResponse()
        );
    }
}
