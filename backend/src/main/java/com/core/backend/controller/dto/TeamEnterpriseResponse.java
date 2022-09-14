package com.core.backend.controller.dto;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TeamEnterpriseResponse {

    private List<EnterpriseResponse> ing;

    private List<EnterpriseResponse> yet;

}
