package com.core.backend.domain;

import com.core.backend.domain.enums.Position;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
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

    private Integer laneStatus;

    private Integer fightStatus;

    private Integer operationStatus;
    ;
    private String roamingStatus;

    private String gankingStatus;

    private String junglingStatus;

    private Float first;

    private Float middle;

    private Float end;

    @Enumerated(EnumType.STRING)
    private Position position;

    private Integer tier;

}
