package com.core.backend.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
public class LeagueTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private BaseTeam baseTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "league_id")
    private League league;

    @Column(columnDefinition = "varchar(20) default '0'")
    private Integer winPoint;

    @Column(columnDefinition = "varchar(20) default '0'")
    private Integer matchWin;

    @Column(columnDefinition = "varchar(20) default '0'")
    private Integer matchLose;

    @Column(columnDefinition = "varchar(20) default '0'")
    private Integer setWin;

    @Column(columnDefinition = "varchar(20) default '0'")
    private Integer setLose;


}
