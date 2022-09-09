package com.core.backend.controller.dto;


import com.core.backend.domain.Sponsor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class SponsorResponse {

    private Long id;

    private String name;

    private String description;

    private Integer money;

    private String image;

    private Integer win;

    public static SponsorResponse of(Sponsor sponsor) {
        return new SponsorResponse(
                sponsor.getId(),
                sponsor.getName(),
                sponsor.getDescription(),
                sponsor.getMoney(),
                sponsor.getImage(),
                sponsor.getWin()
           );
    }
}
