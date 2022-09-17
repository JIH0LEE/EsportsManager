package com.core.backend.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@DynamicInsert
public class GameSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long myTeam;

    private Long oppositeTeam;

    private Long blueTopChamp;

    private Long blueJngChamp;

    private Long blueMidChamp;

    private Long blueAdcChamp;

    private Long blueSupChamp;

    private Long redTopChamp;

    private Long redJngChamp;

    private Long redMidChamp;

    private Long redAdcChamp;

    private Long redSupChamp;

    @Column(columnDefinition = "varchar(20) default '1000'")
    private Integer blueTopTower1;

    @Column(columnDefinition = "varchar(20) default '1000'")
    private Integer blueMidTower1;

    @Column(columnDefinition = "varchar(20) default '1000'")
    private Integer blueBottomTower1;

    @Column(columnDefinition = "varchar(20) default '1000'")
    private Integer blueTopTower2;

    @Column(columnDefinition = "varchar(20) default '1000'")
    private Integer blueMidTower2;

    @Column(columnDefinition = "varchar(20) default '1000'")
    private Integer blueBottomTower2;

    @Column(columnDefinition = "varchar(20) default '1000'")
    private Integer redTopTower1;

    @Column(columnDefinition = "varchar(20) default '1000'")
    private Integer redMidTower1;

    @Column(columnDefinition = "varchar(20) default '1000'")
    private Integer redBottomTower1;

    @Column(columnDefinition = "varchar(20) default '1000'")
    private Integer redTopTower2;

    @Column(columnDefinition = "varchar(20) default '1000'")
    private Integer redMidTower2;

    @Column(columnDefinition = "varchar(20) default '1000'")
    private Integer redBottomTower2;

    @Column(columnDefinition = "varchar(20) default '500'")
    private Integer blueTopGold;

    @Column(columnDefinition = "varchar(20) default '500'")
    private Integer blueJngGold;

    @Column(columnDefinition = "varchar(20) default '500'")
    private Integer blueMidGold;

    @Column(columnDefinition = "varchar(20) default '500'")
    private Integer blueAdcGold;

    @Column(columnDefinition = "varchar(20) default '500'")
    private Integer blueSupGold;

    @Column(columnDefinition = "varchar(20) default '500'")
    private Integer redTopGold;

    @Column(columnDefinition = "varchar(20) default '500'")
    private Integer redJngGold;

    @Column(columnDefinition = "varchar(20) default '500'")
    private Integer redMidGold;

    @Column(columnDefinition = "varchar(20) default '500'")
    private Integer redAdcGold;

    @Column(columnDefinition = "varchar(20) default '500'")
    private Integer redSupGold;

    @Column(columnDefinition = "varchar(20) default '0'")
    private Integer blueDragonCount;

    @Column(columnDefinition = "varchar(20) default '0'")
    private Integer redDragonCount;

    private Integer blueBaron;

    private Integer redBaron;

    private Integer lastDragonTime;

    private Integer lastBaronTime;

    private Integer curTime;

    private boolean isBlue;

    private boolean finished;

    @Column(length = 10000, columnDefinition = "varchar(20) default ''")
    private String gameLog;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_match_id")
    private GameMatch gameMatch;

    public void changeGold(boolean isBlue, String position, int gold) {
        if (isBlue) {
            if (position.equals("TOP")) {
                this.blueTopGold += gold;
            }
            if (position.equals("JUNGLE")) {
                this.blueJngGold += gold;
            }
            if (position.equals("MIDDLE")) {
                this.blueMidGold += gold;
            }
            if (position.equals("ADC")) {
                this.blueAdcGold += gold;
            }
            if (position.equals("SUPPORT")) {
                this.blueSupGold += gold;
            }
        } else {
            if (position.equals("TOP")) {
                this.redTopGold += gold;
            }
            if (position.equals("JUNGLE")) {
                this.redJngGold += gold;
            }
            if (position.equals("MIDDLE")) {
                this.redMidGold += gold;
            }
            if (position.equals("ADC")) {
                this.redAdcGold += gold;
            }
            if (position.equals("SUPPORT")) {
                this.redSupGold += gold;
            }
        }
    }

    public void changeAllGold(boolean isBlue, int gold) {
        if (isBlue) {
            this.blueTopGold += gold;
            this.blueJngGold += gold;
            this.blueMidGold += gold;
            this.blueAdcGold += gold + 100;
            this.blueSupGold += gold;
        } else {
            this.redTopGold += gold;
            this.redJngGold += gold;
            this.redMidGold += gold;
            this.redAdcGold += gold + 100;
            this.redSupGold += gold;
        }
    }

    public Integer getMyGold(String position) {
        if (isBlue) {
            if (position.equals("TOP")) {
                return this.blueTopGold;
            }
            if (position.equals("JUNGLE")) {
                return this.blueJngGold;
            }
            if (position.equals("MIDDLE")) {
                return this.blueMidGold;
            }
            if (position.equals("ADC")) {
                return this.blueAdcGold;
            }
            if (position.equals("SUPPORT")) {
                return this.blueSupGold;
            }
        } else {
            if (position.equals("TOP")) {
                return this.redTopGold;
            }
            if (position.equals("JUNGLE")) {
                return this.redJngGold;
            }
            if (position.equals("MIDDLE")) {
                return this.redMidGold;
            }
            if (position.equals("ADC")) {
                return this.redAdcGold;
            }
            if (position.equals("SUPPORT")) {
                return this.redSupGold;
            }
        }
        return 0;
    }

    public Integer getOpGold(String position) {
        if (isBlue) {
            if (position.equals("TOP")) {
                return this.redTopGold;
            }
            if (position.equals("JUNGLE")) {
                return this.redJngGold;
            }
            if (position.equals("MIDDLE")) {
                return this.redMidGold;
            }
            if (position.equals("ADC")) {
                return this.redAdcGold;
            }
            if (position.equals("SUPPORT")) {
                return this.redSupGold;
            }
        } else {
            if (position.equals("TOP")) {
                return this.blueTopGold;
            }
            if (position.equals("JUNGLE")) {
                return this.blueJngGold;
            }
            if (position.equals("MIDDLE")) {
                return this.blueMidGold;
            }
            if (position.equals("ADC")) {
                return this.blueAdcGold;
            }
            if (position.equals("SUPPORT")) {
                return this.blueSupGold;
            }
        }
        return 0;
    }

    private String makeTimeLog() {
        int min = getCurTime() * 60;
        int max = min + 120;
        int secondSum = (int) ((Math.random() * (max - min)) + min);
        String minute = Integer.toString(secondSum / 60);
        String second = Integer.toString(secondSum % 60);
        return minute + "분 " + second + "초/";
    }

    public void destroyTower(boolean isBlue, String position, int damage) {
        if (isBlue) {
            if (position.equals("TOP")) {
                if (redTopTower1 != 0) {
                    redTopTower1 -= damage;
                    if (redTopTower1 <= 0) {
                        redTopTower1 = 0;
                        changeAllGold(true, 100);
                        String log = makeTimeLog() + "레드팀 탑 외각 타워가 파괴되었습니다.;";
                        System.out.println(log);
                        addLog(log);
                    }
                } else {
                    redTopTower2 -= damage;
                    if (redTopTower2 <= 0) {
                        redTopTower2 = 0;
                        changeAllGold(true, 200);
                        String log = makeTimeLog() + "레드팀 탑 내각 타워가 파괴되었습니다.;";
                        System.out.println(log);
                        addLog(log);
                    }
                }
            }
            if (position.equals("MIDDLE")) {
                if (redMidTower1 != 0) {
                    redMidTower1 -= damage;
                    if (redMidTower1 <= 0) {
                        redMidTower1 = 0;
                        changeAllGold(true, 100);
                        String log = makeTimeLog() + "레드팀 미드 외각 타워가 파괴되었습니다.;";
                        System.out.println(log);
                        addLog(log);
                    }
                } else {
                    redMidTower2 -= damage;
                    if (redMidTower2 <= 0) {
                        redMidTower2 = 0;
                        changeAllGold(true, 200);
                        String log = makeTimeLog() + "레드팀 미드 내각 타워가 파괴되었습니다.;";
                        System.out.println(log);
                        addLog(log);
                    }
                }
            }
            if (position.equals("ADC")) {
                if (redBottomTower1 != 0) {
                    redBottomTower1 -= damage;
                    if (redBottomTower1 <= 0) {
                        redBottomTower1 = 0;
                        changeAllGold(true, 100);
                        String log = makeTimeLog() + "레드팀 바텀 외각 타워가 파괴되었습니다.;";
                        System.out.println(log);
                        addLog(log);
                    }
                } else {
                    redBottomTower2 -= damage;
                    if (redBottomTower2 <= 0) {
                        redBottomTower2 = 0;
                        changeAllGold(true, 200);
                        String log = makeTimeLog() + "레드팀 바텀 내각 타워가 파괴되었습니다.;";
                        System.out.println(log);
                        addLog(log);
                    }
                }
            }
        } else {
            if (position.equals("TOP")) {
                if (blueTopTower1 != 0) {
                    blueTopTower1 -= damage;
                    if (blueTopTower1 <= 0) {
                        blueTopTower1 = 0;
                        changeAllGold(false, 100);
                        String log = makeTimeLog() + "블루팀 탑 외각 타워가 파괴되었습니다.;";
                        System.out.println(log);
                        addLog(log);
                    }
                } else {
                    blueTopTower2 -= damage;
                    if (blueTopTower2 <= 0) {
                        blueTopTower2 = 0;
                        changeAllGold(false, 200);
                        String log = makeTimeLog() + "블루팀 탑 내각 타워가 파괴되었습니다.;";
                        System.out.println(log);
                        addLog(log);
                    }
                }
            }
            if (position.equals("MIDDLE")) {
                if (blueMidTower1 != 0) {
                    blueMidTower1 -= damage;
                    if (blueMidTower1 <= 0) {
                        blueMidTower1 = 0;
                        changeAllGold(false, 100);
                        String log = makeTimeLog() + "블루팀 미드 외각 타워가 파괴되었습니다.;";
                        System.out.println(log);
                        addLog(log);
                    }
                } else {
                    blueMidTower2 -= damage;
                    if (blueMidTower2 <= 0) {
                        blueMidTower2 = 0;
                        changeAllGold(false, 200);
                        String log = makeTimeLog() + "블루팀 미드 내각 타워가 파괴되었습니다.;";
                        System.out.println(log);
                        addLog(log);
                    }
                }
            }
            if (position.equals("ADC")) {
                if (blueBottomTower1 != 0) {
                    blueBottomTower1 -= damage;
                    if (blueBottomTower1 <= 0) {
                        blueBottomTower1 = 0;
                        changeAllGold(false, 100);
                        String log = makeTimeLog() + "블루팀 바텀 외각 타워가 파괴되었습니다.;";
                        System.out.println(log);
                        addLog(log);
                    }
                } else {
                    blueBottomTower2 -= damage;
                    if (blueBottomTower2 <= 0) {
                        blueBottomTower2 = 0;
                        changeAllGold(false, 200);
                        String log = makeTimeLog() + "블루팀 바텀 내각 타워가 파괴되었습니다.;";
                        System.out.println(log);
                        addLog(log);
                    }
                }
            }
        }

    }

    public void destroyTower(boolean isBlue, int damage) {
        if (isBlue) {
            if (redTopTower1 != 0) {
                redTopTower1 -= damage;
                if (redTopTower1 <= 0) {
                    redTopTower1 = 0;
                    changeAllGold(true, 100);
                    String log = makeTimeLog() + "레드팀 탑 외각 타워가 파괴되었습니다.;";
                    System.out.println(log);
                    addLog(log);
                }
                return;
            }
            if (redMidTower1 != 0) {
                redMidTower1 -= damage;
                if (redMidTower1 <= 0) {
                    redMidTower1 = 0;
                    changeAllGold(true, 100);
                    String log = makeTimeLog() + "레드팀 미드 외각 타워가 파괴되었습니다.;";
                    System.out.println(log);
                    addLog(log);
                }
                return;
            }
            if (redBottomTower1 != 0) {
                redBottomTower1 -= damage;
                if (redBottomTower1 <= 0) {
                    redBottomTower1 = 0;
                    changeAllGold(true, 100);
                    String log = makeTimeLog() + "레드팀 바텀 외각 타워가 파괴되었습니다.;";
                    System.out.println(log);
                    addLog(log);
                }
                return;
            }
            if (redTopTower2 != 0) {
                redTopTower2 -= damage;
                if (redTopTower2 <= 0) {
                    redTopTower2 = 0;
                    changeAllGold(true, 200);
                    String log = makeTimeLog() + "레드팀 탑 내각 타워가 파괴되었습니다.;";
                    System.out.println(log);
                    addLog(log);
                }
                return;
            }
            if (redMidTower2 != 0) {
                redMidTower2 -= damage;
                if (redMidTower2 <= 0) {
                    redMidTower2 = 0;
                    changeAllGold(true, 200);
                    String log = makeTimeLog() + "레드팀 미드 내각 타워가 파괴되었습니다.;";
                    System.out.println(log);
                    addLog(log);
                }
                return;
            }
            if (redBottomTower2 != 0) {
                redBottomTower2 -= damage;
                if (redBottomTower2 <= 0) {
                    redBottomTower2 = 0;
                    changeAllGold(true, 200);
                    String log = makeTimeLog() + "레드팀 바텀 내각 타워가 파괴되었습니다.;";
                    System.out.println(log);
                    addLog(log);
                }
                return;
            }
        } else {
            if (blueTopTower1 != 0) {
                blueTopTower1 -= damage;
                if (blueTopTower1 <= 0) {
                    blueTopTower1 = 0;
                    changeAllGold(true, 100);
                    String log = makeTimeLog() + "블루팀 탑 외각 타워가 파괴되었습니다.;";
                    System.out.println(log);
                    addLog(log);
                }
                return;
            }
            if (blueMidTower1 != 0) {
                blueMidTower1 -= damage;
                if (blueMidTower1 <= 0) {
                    blueMidTower1 = 0;
                    changeAllGold(true, 100);
                    String log = makeTimeLog() + "블루팀 미드 외각 타워가 파괴되었습니다.;";
                    System.out.println(log);
                    addLog(log);
                }
                return;
            }
            if (blueBottomTower1 != 0) {
                blueBottomTower1 -= damage;
                if (blueBottomTower1 <= 0) {
                    blueBottomTower1 = 0;
                    changeAllGold(true, 100);
                    String log = makeTimeLog() + "블루팀 바텀 외각 타워가 파괴되었습니다.;";
                    System.out.println(log);
                    addLog(log);
                }
                return;
            }
            if (blueTopTower2 != 0) {
                blueTopTower2 -= damage;
                if (blueTopTower2 <= 0) {
                    blueTopTower2 = 0;
                    changeAllGold(true, 200);
                    String log = makeTimeLog() + "블루팀 탑 내각 타워가 파괴되었습니다.;";
                    System.out.println(log);
                    addLog(log);
                }
                return;
            }
            if (blueMidTower2 != 0) {
                blueMidTower2 -= damage;
                if (blueMidTower2 <= 0) {
                    blueMidTower2 = 0;
                    changeAllGold(true, 200);
                    String log = makeTimeLog() + "블루팀 미드 내각 타워가 파괴되었습니다.;";
                    System.out.println(log);
                    addLog(log);
                }
                return;
            }
            if (blueBottomTower2 != 0) {
                blueBottomTower2 -= damage;
                if (blueBottomTower2 <= 0) {
                    blueBottomTower2 = 0;
                    changeAllGold(true, 200);
                    String log = makeTimeLog() + "블루팀 바텀 내각 타워가 파괴되었습니다.;";
                    System.out.println(log);
                    addLog(log);
                }
                return;
            }
        }

    }

    public int getMyGold() {
        int gold;
        if (isBlue) {
            gold = blueTopGold + blueJngGold + blueMidGold + blueAdcGold + blueSupGold;
        } else {
            gold = redTopGold + redJngGold + redMidGold + redAdcGold + redSupGold;
        }
        return gold;
    }

    public int getOpGold() {
        int gold;
        if (isBlue) {
            gold = redTopGold + redJngGold + redMidGold + redAdcGold + redSupGold;
        } else {
            gold = blueTopGold + blueJngGold + blueMidGold + blueAdcGold + blueSupGold;
        }
        return gold;
    }

    public void updateTime() {
        curTime = curTime + 2;
    }

    public boolean isBlueWinable() {
        if (redTopTower1 == 0 && redTopTower2 == 0 && redMidTower1 == 0 &&
            redMidTower2 == 0 && redBottomTower1 == 0 && redBottomTower2 == 0) {
            return true;
        }
        return false;
    }

    public boolean isRedWinable() {
        if (blueTopTower1 == 0 && blueTopTower2 == 0 && blueMidTower1 == 0 &&
            blueMidTower2 == 0 && blueBottomTower1 == 0 && blueBottomTower2 == 0) {
            return true;
        }
        return false;
    }

    public void finishGame() {
        finished = true;
    }

    public void addLog(String log) {
        gameLog += log;
    }

    public List<Long> getChampList() {
        List<Long> result = new ArrayList<>();
        result.add(blueTopChamp);
        result.add(blueJngChamp);
        result.add(blueMidChamp);
        result.add(blueAdcChamp);
        result.add(blueSupChamp);
        result.add(redTopChamp);
        result.add(redJngChamp);
        result.add(redMidChamp);
        result.add(redAdcChamp);
        result.add(redSupChamp);
        return result;
    }

    public List<Integer> getTowerList() {
        List<Integer> result = new ArrayList<>();
        result.add(blueTopTower1);
        result.add(blueTopTower2);
        result.add(blueMidTower1);
        result.add(blueMidTower2);
        result.add(blueBottomTower1);
        result.add(blueBottomTower2);
        result.add(redTopTower1);
        result.add(redTopTower2);
        result.add(redMidTower1);
        result.add(redMidTower2);
        result.add(redBottomTower1);
        result.add(redBottomTower2);
        return result;
    }

    public List<Integer> getGoldList() {
        List<Integer> result = new ArrayList<>();
        result.add(blueTopGold);
        result.add(blueJngGold);
        result.add(blueMidGold);
        result.add(blueAdcGold);
        result.add(blueSupGold);
        result.add(redTopGold);
        result.add(redJngGold);
        result.add(redMidGold);
        result.add(redAdcGold);
        result.add(redSupGold);
        return result;
    }
}
