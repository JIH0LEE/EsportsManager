package com.core.backend.controller.dto;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TeamSponsorResponse {

    private List<SponsorResponse> alreadySponsor;

    private List<SponsorResponse> disableSponsor;

    private List<SponsorResponse> enableSponsor;

}
