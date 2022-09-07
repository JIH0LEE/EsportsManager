package com.core.backend.controller.dto;

import com.core.backend.domain.MyTeam;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class MyTeamResponse {

    private Long id;

    private String name;

    private HeadCoachResponse headCoach;

    private MyPlayerResponse top;

    private MyPlayerResponse jng;

    private MyPlayerResponse mid;

    private MyPlayerResponse adc;

    private MyPlayerResponse sup;

    private List<MyPlayerResponse> sub;

    public static MyTeamResponse of(MyTeam myTeam) {
        List<MyPlayerResponse> myPlayerResponseList = myTeam.getMyPlayerList()
                .stream()
                .map(MyPlayerResponse::of)
                .collect(Collectors.toList());
        MyPlayerResponse top = myPlayerResponseList
                .stream()
                .filter(myPlayerResponse -> myPlayerResponse.getPosition() == "TOP")
                .findFirst()
                .orElseThrow();
        MyPlayerResponse jng = myPlayerResponseList
                .stream()
                .filter(myPlayerResponse -> myPlayerResponse.getPosition() == "JUNGLE")
                .findFirst()
                .orElseThrow();
        MyPlayerResponse mid = myPlayerResponseList
                .stream()
                .filter(myPlayerResponse -> myPlayerResponse.getPosition() == "MIDDLE")
                .findFirst()
                .orElseThrow();
        MyPlayerResponse adc = myPlayerResponseList
                .stream()
                .filter(myPlayerResponse -> myPlayerResponse.getPosition() == "ADC")
                .findFirst()
                .orElseThrow();
        MyPlayerResponse sup = myPlayerResponseList
                .stream()
                .filter(myPlayerResponse -> myPlayerResponse.getPosition() == "SUPPORT")
                .findFirst()
                .orElseThrow();
        List<MyPlayerResponse> sub = myPlayerResponseList
                .stream()
                .filter(myPlayerResponse -> myPlayerResponse.getPosition() == "SUB")
                .collect(Collectors.toList());
        return new MyTeamResponse(
                myTeam.getId(),
                myTeam.getName(),
                HeadCoachResponse.of(myTeam.getHeadCoach()),
                top, jng, mid, adc, sup, sub
        );
    }
}
