package com.core.backend.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PersonalScheduleListRequest {

    private Long headCoachId;

    @JsonProperty("game")
    private boolean isGame;

    @JsonProperty("submitData")
    private List<PersonalScheduleRequest> personalScheduleRequestList;
}
