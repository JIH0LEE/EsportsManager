package com.core.backend.controller.dto;


import com.core.backend.domain.Sponsor;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TeamSponsorResponse {

    private List<SponsorResponse> alreadySponsor;

    private List<SponsorResponse> disableSponsor;

    private List<SponsorResponse> enableSponsor;

}
