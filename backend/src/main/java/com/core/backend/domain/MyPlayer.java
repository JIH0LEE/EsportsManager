package com.core.backend.domain;

import com.core.backend.domain.enums.Condition;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    public void update(String position) {
        this.position = position;
    }

    public Integer getAllPower() {
        return player.getAllPower() + level * 6;
    }

    public void applySchedule(PersonalSchedule schedule) {
        applyExp(schedule.getExp());
        applyStatus(schedule.getStatus());
    }

    private void applyStatus(Integer status) {
        if (status == -2) {
            this.status = Condition.BAD;
        } else if (status == 2) {
            this.status = Condition.GOOD;
        } else if (status == 1) {
            if (this.status.equals(Condition.BAD)) {
                this.status = Condition.NORMAL;
            } else {
                this.status = Condition.GOOD;
            }
        } else if (status == -1) {
            if (this.status.equals(Condition.GOOD)) {
                this.status = Condition.NORMAL;
            } else {
                this.status = Condition.BAD;
            }
        }
    }

    public void applyExp(Integer exp) {
        if (level == 10) {
            return;
        }
        this.exp = this.exp + exp;
        if (this.exp >= 100) {
            level = level + 1;
            this.exp = this.exp - 100;
        }
    }

    public float getConditionInfo() {
        if (status == Condition.BAD) {
            return 0.9f;
        }
        if (status == Condition.GOOD) {
            return 1.1f;
        }
        return 1.0f;
    }

    public int getDistinctPower(String status) {
        float condition = getConditionInfo();
        if (status.equals("LANE")) {
            return (int) ((this.player.getLaneStatus() + this.level) * condition);
        }
        if (status.equals("OPERATION")) {
            return (int) ((this.player.getOperationStatus() + this.level) * condition);
        }
        if (status.equals("JUNGLING")) {
            return (int) ((this.player.getJunglingStatus() + this.level) * condition);
        }
        if (status.equals("GANKING")) {
            return (int) ((this.player.getGankingStatus() + this.level) * condition);
        }
        if (status.equals("ROAMING")) {
            return (int) ((this.player.getRoamingStatus() + this.level) * condition);
        }
        if (status.equals("FIGHT")) {
            return (int) ((this.player.getFightStatus() + this.level) * condition);
        }
        return 0;
    }
}
