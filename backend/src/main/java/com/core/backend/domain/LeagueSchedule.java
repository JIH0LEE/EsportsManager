package com.core.backend.domain;

import com.core.backend.domain.enums.Position;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Entity
@AllArgsConstructor
@Getter
@Builder
public class LeagueSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer day;

    @Column(name = "team1_id")
    private Long team1Id;

    @Column(name = "team2_id")
    private Long team2Id;

    private boolean isGame;

    private boolean isPlayoff;

    public LeagueSchedule() {

    }
}
