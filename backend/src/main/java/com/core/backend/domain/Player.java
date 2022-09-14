package com.core.backend.domain;

import com.core.backend.domain.enums.Grade;
import com.core.backend.domain.enums.Position;
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

    private Integer roamingStatus;

    private Integer gankingStatus;

    private Integer junglingStatus;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    private Integer price;

    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "base_team_id")
    private BaseTeam baseTeam;

    public Integer getAllPower() {
        return laneStatus + fightStatus + operationStatus + roamingStatus + gankingStatus + junglingStatus;
    }

    public int getDistinctPower(String status) {
        if (status.equals("LANE")) {
            return this.getLaneStatus();
        }
        if (status.equals("OPERATION")) {
            return this.getOperationStatus();
        }
        if (status.equals("JUNGLING")) {
            return this.getJunglingStatus();
        }
        if (status.equals("GANKING")) {
            return this.getGankingStatus();
        }
        if (status.equals("ROAMING")) {
            return this.getRoamingStatus();
        }
        if (status.equals("FIGHT")) {
            return this.getFightStatus();
        }
        return 0;
    }

}
