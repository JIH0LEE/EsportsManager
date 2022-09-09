package com.core.backend.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TeamEnterpriseResponse {

    private List<EnterpriseResponse> ing;

    private List<EnterpriseResponse> yet;

}
