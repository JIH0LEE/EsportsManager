package com.core.backend.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PersonalScheduleListRequest {

    private Long headCoachId;

    @JsonProperty("game")
    private boolean isGame;

    @JsonProperty("submitData")
    private List<PersonalScheduleRequest> personalScheduleRequestList;
}
