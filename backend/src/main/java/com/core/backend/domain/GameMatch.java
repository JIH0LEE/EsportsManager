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
public class GameMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "my_team_id")
    private MyTeam myTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oppostite_team_id")
    private BaseTeam oppositeTeam;

    private boolean finish;

    private Integer gameSetCount;

    private Integer gameScore;

    private boolean isBlue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "league_id")
    private League league;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "gameMatch", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GameSet> gameSets = new ArrayList<>();

    public void updateSetInfo(int win){
        gameSetCount+=1;
        gameScore +=win;;
        if((gameScore==1 || gameScore==-1) &&gameSetCount==3){
            finish = true;

        }
        if(gameScore==2 || gameScore==-2){
            finish = true;
        }
    }

}
