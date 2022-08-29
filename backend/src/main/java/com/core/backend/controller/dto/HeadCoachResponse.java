package com.core.backend.controller.dto;

import com.core.backend.domain.HeadCoach;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HeadCoachResponse {

    private Long id;

    private String name;

    public static HeadCoachResponse of(HeadCoach headCoach){
        return new HeadCoachResponse(headCoach.getId(), headCoach.getName());
    }

}
