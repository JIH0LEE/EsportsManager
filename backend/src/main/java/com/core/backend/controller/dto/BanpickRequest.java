package com.core.backend.controller.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class BanpickRequest {
    private Long headCoachId;

    private Long matchId;

    private List<Long> bluePick;

    private List<Long> redPick;

    private boolean isBlue;

    private Long myTeam;

    private Long oppositeTeam;

}
