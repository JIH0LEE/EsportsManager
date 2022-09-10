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

import com.core.backend.exception.NotEnoughMoney;
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

    private Integer money;

    private Long sponsor1;

    private Long sponsor2;

    private Long sponsor3;

    private Long enterprise1;

    private Long enterprise2;

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

    public List<Long> getSponsors(){
        List<Long> sponsors = new ArrayList<Long>();
        if(sponsor1!=null){
            sponsors.add(sponsor1);
        }
        if(sponsor2!=null){
            sponsors.add(sponsor2);
        }
        if(sponsor3!=null){
            sponsors.add(sponsor3);
        }
        return sponsors;
    }

    public List<Long> getEnterprises(){
        List<Long> enterprises = new ArrayList<Long>();
        if(enterprise1!=null){
            enterprises.add(enterprise1);
        }
        if(enterprise2!=null){
            enterprises.add(enterprise2);
        }
        return enterprises;
    }

    public void contractSponsor(Long id){
        if(sponsor1==null){
            sponsor1 = id;
        }
        else if(sponsor2==null){
            sponsor2 =id;
        }
        else{
            sponsor3 = id;
        }
    }
    public void startEnterprise(Long id){
        if(enterprise1==null){
            enterprise1 = id;
        }
        else{
            enterprise2 = id;
        }
        if(id==1L){
            if(money<20000000){
                throw new NotEnoughMoney();
            }
            money = money - 20000000;
        }
        else{
            if(money<100000000){
                throw new NotEnoughMoney();
            }
            money = money - 100000000;
        }
    }
}
