package com.core.backend.controller.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

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
