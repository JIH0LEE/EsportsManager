package com.core.backend.domain;

import com.core.backend.domain.enums.Grade;
import com.core.backend.domain.enums.Position;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickName;

    private String realName;

    @Enumerated(EnumType.STRING)
    private Position position;

    private Integer laneStatus;

    private Integer fightStatus;

    private Integer operationStatus;

    private String roamingStatus;

    private String gankingStatus;

    private String junglingStatus;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    private Integer price;

    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "base_team_id")
    private BaseTeam baseTeam;


}
