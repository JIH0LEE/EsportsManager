package com.core.backend.domain;

import com.core.backend.domain.enums.Grade;
import com.core.backend.domain.enums.Position;

import javax.persistence.*;

@Entity
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

    private Integer popularityStatus;

    private Grade grade;

    private Integer price;

    private String hiddenStatus;

}
