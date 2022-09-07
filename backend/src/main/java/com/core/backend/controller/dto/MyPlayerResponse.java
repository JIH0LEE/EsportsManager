package com.core.backend.controller.dto;

import com.core.backend.domain.MyPlayer;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class MyPlayerResponse {

    private Long id;

    private Integer level;

    private String position;

    @JsonProperty("player")
    private PlayerResponse playerResponse;

    public static MyPlayerResponse of(MyPlayer myPlayer) {
        return new MyPlayerResponse(
                myPlayer.getId(),
                myPlayer.getLevel(),
                myPlayer.getPosition(),
                PlayerResponse.of(myPlayer.getPlayer())
        );
    }
}
