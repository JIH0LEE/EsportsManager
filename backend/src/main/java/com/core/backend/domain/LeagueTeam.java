package com.core.backend.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
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
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    public void updateWinPoint(int score) {
        if (score == 1) {
            this.setWin = this.setWin + 2;
            this.setLose = this.setLose + 1;
            this.matchWin = this.matchWin + 1;
            this.winPoint = this.winPoint + 1;
        } else if (score == -1) {
            this.setWin = this.setWin + 1;
            this.setLose = this.setLose + 2;
            this.matchLose = this.matchLose + 1;
            this.winPoint = this.winPoint - 1;
        } else if (score == 2) {
            this.setWin = this.setWin + 2;
            this.matchWin = this.matchWin + 1;
            this.winPoint = this.winPoint + 2;
        } else {
            this.setLose = this.setLose + 2;
            this.matchLose = this.matchLose + 1;
            this.winPoint = this.winPoint - 2;
        }
    }

}
