package com.core.backend.domain;

import javax.persistence.*;

import com.core.backend.domain.enums.Condition;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MyPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer level;

    private Integer exp;

    private String position;

    @Enumerated(EnumType.STRING)
    private Condition status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "my_team_id")
    private MyTeam myTeam;

    public void update(String position){
        this.position = position;
    }

    public Integer getAllPower(){
        return player.getAllPower()+level*6;
    }

}
