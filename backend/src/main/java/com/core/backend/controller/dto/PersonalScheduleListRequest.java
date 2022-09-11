package com.core.backend.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class PersonalScheduleListRequest {

    private Long headCoachId;

    @JsonProperty("submitData")
    private List<PersonalScheduleRequest> personalScheduleRequestList;
}
