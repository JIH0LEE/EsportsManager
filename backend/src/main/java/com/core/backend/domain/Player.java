package com.core.backend.domain;

import com.core.backend.domain.enums.Grade;
import com.core.backend.domain.enums.Position;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
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

    @Enumerated(EnumType.STRING)
    private Grade grade;

    private Integer price;

    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "base_team_id")
    private BaseTeam baseTeam;

    public Player() {

    }
}
