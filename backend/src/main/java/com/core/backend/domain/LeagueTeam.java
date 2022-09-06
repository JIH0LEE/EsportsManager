package com.core.backend.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeagueTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private BaseTeam baseTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "league_id")
    private League league;

    @ColumnDefault("0")
    private Integer winPoint;

    @ColumnDefault("0")
    private Integer matchWin;

    @ColumnDefault("0")
    private Integer matchLose;

    @ColumnDefault("0")
    private Integer setWin;

    @ColumnDefault("0")
    private Integer setLose;

}
