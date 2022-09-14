package com.core.backend.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "head_coach_id")
    private HeadCoach headCoach;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "my_team_id")
    private MyTeam myTeam;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "league", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<LeagueTeam> leagueTeamList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "league", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<GameMatch> gameMatchList = new ArrayList<>();

    private Integer day;

    private boolean finish;

    public void addDay() {
        day = day + 1;
    }
}
