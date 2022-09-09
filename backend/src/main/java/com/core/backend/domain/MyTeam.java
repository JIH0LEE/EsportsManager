package com.core.backend.domain;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
public class MyTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

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

    @OneToOne
    @JoinColumn(name = "head_coach_id")
    private HeadCoach headCoach;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "base_team_id")
    private BaseTeam baseTeam;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "myTeam", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MyPlayer> myPlayerList = new ArrayList<>();

    public void updateWinPoint(int score){
        if(score==1){
            this.setWin =this.setWin+2;
            this.setLose =this.setLose+1;
            this.matchWin=this.matchWin+1;
            this.winPoint=this.winPoint+1;
        }
        else if(score==-1){
            this.setWin =this.setWin+1;
            this.setLose =this.setLose+2;
            this.matchLose=this.matchLose+1;
            this.winPoint=this.winPoint-1;
        }
        else if(score==2){
            this.setWin =this.setWin+2;
            this.matchWin=this.matchWin+1;
            this.winPoint=this.winPoint+2;
        }
        else{
            this.setLose =this.setLose+2;
            this.matchLose=this.matchLose+1;
            this.winPoint=this.winPoint-2;
        }
    }

}
