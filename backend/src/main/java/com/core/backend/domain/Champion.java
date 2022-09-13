package com.core.backend.domain;

import com.core.backend.domain.enums.Position;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class Champion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String koreanName;

    private String englishName;

    private String image;

    private Integer laneStatus;

    private Integer fightStatus;

    private Integer operationStatus;
    ;
    private Integer roamingStatus;

    private Integer gankingStatus;

    private Integer junglingStatus;

    private Float first;

    private Float middle;

    private Float end;

    @Enumerated(EnumType.STRING)
    private Position position;

    private Integer tier;

    private float getTimeValue(String time){
        float timeValue = 1f;

        if(Objects.equals(time, "FIRST")){
            timeValue = first;
        }
        if(Objects.equals(time, "MIDDLE")){
            timeValue = middle;
        }
        if(Objects.equals(time, "END")){
            timeValue = end;
        }

        return timeValue;
    }

    private float getTierValue(){
        if(tier == 1){
            return 1.2f;
        }
        if(tier == 2){
            return 1.1f;
        }if(tier == 3){
            return 1.0f;
        }if(tier == 4){
            return 0.9f;
        }if(tier == 5){
            return 0.8f;
        }
        return 1.0f;
    }

    public int getDistinctPower(String status, String time){

        float timeValue = getTimeValue(time);
        float tierValue = getTierValue();
        float finalValue = timeValue * tierValue;

        if(status.equals("LANE")){
            return (int) (this.getLaneStatus()*finalValue);
        }
        if(status.equals("OPERATION")){
            return (int) (this.getOperationStatus()*finalValue);
        }
        if(status.equals("JUNGLING")){
            return (int) (this.getJunglingStatus()*finalValue);
        }
        if(status.equals("GANKING")){
            return (int) (this.getGankingStatus()*finalValue);
        }
        if(status.equals("ROAMING")){
            return (int) (this.getRoamingStatus()*finalValue);
        }
        if(status.equals("FIGHT")){
            return (int) (this.getFightStatus()*finalValue);
        }
        return 50;
    }

}
