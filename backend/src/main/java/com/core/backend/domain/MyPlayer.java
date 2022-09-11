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

    public void applySchedule(PersonalSchedule schedule){
        applyExp(schedule.getExp());
        applyStatus(schedule.getStatus());
    }

    private void applyStatus(Integer status){
        if(status==-2){
            this.status = Condition.BAD;
        }
        else if(status==2){
            this.status = Condition.GOOD;
        }
        else if(status==1){
            if(this.status.equals(Condition.BAD)){
                this.status = Condition.NORMAL;
            }
            else{
                this.status = Condition.GOOD;
            }
        }
        else if(status==-1){
            if(this.status.equals(Condition.GOOD)){
                this.status = Condition.NORMAL;
            }
            else{
                this.status = Condition.BAD;
            }
        }
    }

    public void applyExp(Integer exp){
        if(level==10){
            return;
        }
        this.exp = this.exp+exp;
        if(this.exp>=100){
            level = level + 1;
            this.exp = this.exp - 100;
        }

    }

}
