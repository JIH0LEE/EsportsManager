package com.core.backend.controller.dto;


import com.core.backend.domain.Enterprise;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EnterpriseResponse {

    private Long id;

    private String name;

    private String description;

    private Integer earningMoney;

    private Integer cost;

    private String image;

    public static EnterpriseResponse of(Enterprise enterprise) {
        return new EnterpriseResponse(
            enterprise.getId(),
            enterprise.getName(),
            enterprise.getDescription(),
            enterprise.getEarningMoney(),
            enterprise.getCost(),
            enterprise.getImage()
        );
    }
}
