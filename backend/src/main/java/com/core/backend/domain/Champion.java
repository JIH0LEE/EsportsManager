package com.core.backend.domain;

import com.core.backend.domain.enums.Feature;
import com.core.backend.domain.enums.Position;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Getter
@Builder
public class Champion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String koreanName;

    private String englishName;

    private String image;

    @Enumerated(EnumType.STRING)
    private Feature feature;

    @Enumerated(EnumType.STRING)
    private Position position;

    private Integer tier;

    public Champion() {

    }
}
