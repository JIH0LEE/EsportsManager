package com.core.backend.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class LeagueDetailedInfoResponse {

    private boolean success;

    private String message;

    private boolean isGame;

    private boolean isMyGame;

    private List<String> teams;

    private Integer day;

}
