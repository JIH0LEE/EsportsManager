package com.core.backend.domain;

import com.core.backend.domain.enums.Feature;
import com.core.backend.domain.enums.Position;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    private Feature feature;

    private Position position;

    private Integer tier;

    public Champion() {

    }
}
