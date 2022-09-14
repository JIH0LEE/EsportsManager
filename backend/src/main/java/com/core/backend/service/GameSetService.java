package com.core.backend.service;

import com.core.backend.domain.BaseTeam;
import com.core.backend.domain.GameSet;
import com.core.backend.domain.LeagueTeam;
import com.core.backend.domain.MyTeam;
import com.core.backend.domain.enums.Position;
import com.core.backend.domain.repository.ChampionRepository;
import com.core.backend.domain.repository.GameSetRepository;
import com.core.backend.domain.repository.LeagueTeamRepository;
import com.core.backend.domain.repository.MyPlayerRepository;
import com.core.backend.domain.repository.MyTeamRepository;
import com.core.backend.domain.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class GameSetService {

    private final GameSetRepository gameSetRepository;
    private final MyTeamRepository myTeamRepository;
    private final MyPlayerRepository myPlayerRepository;
    private final ChampionRepository championRepository;
    private final PlayerRepository playerRepository;
    private final LeagueTeamRepository leagueTeamRepository;

    private boolean isBigBattle(GameSet gameSet) {
        Integer currentTime = gameSet.getCurTime();
        float rate = 0f;
        if (currentTime <= 5) {
            rate = 0.05f;
        } else if (currentTime <= 10) {
            rate = 0.2f;
        } else if (currentTime <= 20) {
            rate = 0.3f;
        } else if (currentTime <= 30) {
            rate = 0.4f;
        } else {
            rate = 0.5f;
        }
        double rand = Math.random();
        if (rand <= rate) {
            return true;
        } else {
            return false;
        }
    }

    private int getMyDistinctPower(String status, String position, String time, MyTeam myTeam, GameSet gameSet) {
        int player = myPlayerRepository
            .findMyPlayerByMyTeamAndPosition(myTeam, position)
            .getDistinctPower(status);
        int champion = 0;
        if (gameSet.isBlue()) {
            if (position.equals("TOP")) {
                champion = championRepository.findById(gameSet.getBlueTopChamp()).orElseThrow()
                    .getDistinctPower(status, time);
            }
            if (position.equals("JUNGLE")) {
                champion = championRepository.findById(gameSet.getBlueJngChamp()).orElseThrow()
                    .getDistinctPower(status, time);
            }
            if (position.equals("MIDDLE")) {
                champion = championRepository.findById(gameSet.getBlueMidChamp()).orElseThrow()
                    .getDistinctPower(status, time);
            }
            if (position.equals("ADC")) {
                champion = championRepository.findById(gameSet.getBlueAdcChamp()).orElseThrow()
                    .getDistinctPower(status, time);
            }
            if (position.equals("SUPPORT")) {
                champion = championRepository.findById(gameSet.getBlueSupChamp()).orElseThrow()
                    .getDistinctPower(status, time);
            }
        } else {
            if (position.equals("TOP")) {
                champion = championRepository.findById(gameSet.getRedTopChamp()).orElseThrow()
                    .getDistinctPower(status, time);
            }
            if (position.equals("JUNGLE")) {
                champion = championRepository.findById(gameSet.getRedJngChamp()).orElseThrow()
                    .getDistinctPower(status, time);
            }
            if (position.equals("MIDDLE")) {
                champion = championRepository.findById(gameSet.getRedMidChamp()).orElseThrow()
                    .getDistinctPower(status, time);
            }
            if (position.equals("ADC")) {
                champion = championRepository.findById(gameSet.getRedAdcChamp()).orElseThrow()
                    .getDistinctPower(status, time);
            }
            if (position.equals("SUPPORT")) {
                champion = championRepository.findById(gameSet.getRedSupChamp()).orElseThrow()
                    .getDistinctPower(status, time);
            }
        }
        return (player + champion) / 2;
    }

    private int getOpDistinctPower(String status, String position, String time, LeagueTeam opTeam, GameSet gameSet) {
        int player = playerRepository
            .findByBaseTeamAndPosition(opTeam.getBaseTeam(), Position.valueOf(position)).get(0)
            .getDistinctPower(status);
        int champion = 0;
        if (gameSet.isBlue()) {
            if (position.equals("TOP")) {
                champion = championRepository.findById(gameSet.getRedTopChamp()).orElseThrow()
                    .getDistinctPower(status, time);
            }
            if (position.equals("JUNGLE")) {
                champion = championRepository.findById(gameSet.getRedJngChamp()).orElseThrow()
                    .getDistinctPower(status, time);
            }
            if (position.equals("MIDDLE")) {
                champion = championRepository.findById(gameSet.getRedMidChamp()).orElseThrow()
                    .getDistinctPower(status, time);
            }
            if (position.equals("ADC")) {
                champion = championRepository.findById(gameSet.getRedAdcChamp()).orElseThrow()
                    .getDistinctPower(status, time);
            }
            if (position.equals("SUPPORT")) {
                champion = championRepository.findById(gameSet.getRedSupChamp()).orElseThrow()
                    .getDistinctPower(status, time);
            }
        } else {
            if (position.equals("TOP")) {
                champion = championRepository.findById(gameSet.getBlueTopChamp()).orElseThrow()
                    .getDistinctPower(status, time);
            }
            if (position.equals("JUNGLE")) {
                champion = championRepository.findById(gameSet.getBlueJngChamp()).orElseThrow()
                    .getDistinctPower(status, time);
            }
            if (position.equals("MIDDLE")) {
                champion = championRepository.findById(gameSet.getBlueMidChamp()).orElseThrow()
                    .getDistinctPower(status, time);
            }
            if (position.equals("ADC")) {
                champion = championRepository.findById(gameSet.getBlueAdcChamp()).orElseThrow()
                    .getDistinctPower(status, time);
            }
            if (position.equals("SUPPORT")) {
                champion = championRepository.findById(gameSet.getBlueSupChamp()).orElseThrow()
                    .getDistinctPower(status, time);
            }

        }
        return (player + champion) / 2;
    }

    private void detailedRoaming(GameSet gameSet, boolean isBlue, String position) {
        int rand;
        if (position.equals("MIDDLE")) {
            rand = (int) (Math.random() * 2);
        } else {
            rand = (int) (Math.random() * 3);
        }
        if (rand == 0) {
            gameSet.changeGold(isBlue, "TOP", 200);
            gameSet.changeGold(isBlue, position, 200);

        } else if (rand == 1) {
            gameSet.changeGold(isBlue, "ADC", 200);
            gameSet.changeGold(isBlue, position, 200);
        } else {
            gameSet.changeGold(isBlue, "MIDDLE", 200);
            gameSet.changeGold(isBlue, position, 200);
        }
        if (isBlue) {
            System.out.println("블루팀이 로밍에 성공하였습니다");
        } else {
            System.out.println("레드팀이 로밍에 성공하였습니다");
        }
    }

    private void roaming(MyTeam myTeam, LeagueTeam oppositeTeam, GameSet gameSet) {
        int blueMidPlayerScore;
        int blueSupPlayerScore;
        int redMidPlayerScore;
        int redSupPlayerScore;
        if (gameSet.isBlue()) {
            blueMidPlayerScore = getMyDistinctPower("ROAMING", "MIDDLE", "FIRST", myTeam, gameSet);
            if (Math.random() * 100 <= (double) blueMidPlayerScore / 3) {
                detailedRoaming(gameSet, true, "MIDDLE");
            }
            blueSupPlayerScore = getMyDistinctPower("ROAMING", "SUPPORT", "FIRST", myTeam, gameSet);
            if (Math.random() * 100 <= (double) blueSupPlayerScore / 3) {
                detailedRoaming(gameSet, true, "SUPPORT");
            }
            redMidPlayerScore = getOpDistinctPower("ROAMING", "MIDDLE", "FIRST", oppositeTeam, gameSet);
            if (Math.random() * 100 <= (double) redMidPlayerScore / 3) {
                detailedRoaming(gameSet, false, "MIDDLE");
            }
            redSupPlayerScore = getOpDistinctPower("ROAMING", "SUPPORT", "FIRST", oppositeTeam, gameSet);
            if (Math.random() * 100 <= (double) redSupPlayerScore / 3) {
                detailedRoaming(gameSet, false, "SUPPORT");
            }
        } else {
            blueMidPlayerScore = getOpDistinctPower("ROAMING", "MIDDLE", "FIRST", oppositeTeam, gameSet);
            if (Math.random() * 100 <= (double) blueMidPlayerScore / 3) {
                detailedRoaming(gameSet, true, "MIDDLE");
            }
            blueSupPlayerScore = getOpDistinctPower("ROAMING", "SUPPORT", "FIRST", oppositeTeam, gameSet);
            if (Math.random() * 100 <= (double) blueSupPlayerScore / 3) {
                detailedRoaming(gameSet, true, "SUPPORT");
            }
            redMidPlayerScore = getMyDistinctPower("ROAMING", "MIDDLE", "FIRST", myTeam, gameSet);
            if (Math.random() * 100 <= (double) redMidPlayerScore / 3) {
                detailedRoaming(gameSet, false, "MIDDLE");
            }
            redSupPlayerScore = getMyDistinctPower("ROAMING", "SUPPORT", "FIRST", myTeam, gameSet);
            if (Math.random() * 100 <= (double) redSupPlayerScore / 3) {
                detailedRoaming(gameSet, false, "SUPPORT");
            }
        }
    }

    private void detailedGanking(GameSet gameSet, boolean isBlue) {
        int rand = (int) (Math.random() * 3);
        if (rand == 0) {
            gameSet.changeGold(isBlue, "TOP", 200);
            gameSet.changeGold(isBlue, "JUNGLE", 200);
        } else if (rand == 1) {
            gameSet.changeGold(isBlue, "ADC", 200);
            gameSet.changeGold(isBlue, "JUNGLE", 200);
            gameSet.changeGold(isBlue, "SUPPORT", 200);
        } else {
            gameSet.changeGold(isBlue, "MIDDLE", 200);
            gameSet.changeGold(isBlue, "JUNGLE", 200);
        }
        if (isBlue) {
            System.out.println("블루팀이 갱킹에 성공하였습니다");
        } else {
            System.out.println("레드팀이 갱킹에 성공하였습니다");
        }
    }

    private void ganking(MyTeam myTeam, LeagueTeam oppositeTeam, GameSet gameSet) {
        int blueJngPlayerScore;
        int redJngPlayerScore;
        if (gameSet.isBlue()) {
            blueJngPlayerScore = getMyDistinctPower("JUNGLING", "JUNGLE", "FIRST", myTeam, gameSet);
            if (Math.random() * 100 <= (double) blueJngPlayerScore / 3) {
                detailedGanking(gameSet, true);
            }

            redJngPlayerScore = getOpDistinctPower("JUNGLING", "JUNGLE", "FIRST", oppositeTeam, gameSet);
            if (Math.random() * 100 <= (double) redJngPlayerScore / 3) {
                detailedGanking(gameSet, false);
            }

        } else {
            blueJngPlayerScore = getOpDistinctPower("JUNGLING", "JUNGLE", "FIRST", oppositeTeam, gameSet);
            if (Math.random() * 100 <= (double) blueJngPlayerScore / 3) {
                detailedGanking(gameSet, true);
            }
            redJngPlayerScore = getMyDistinctPower("JUNGLING", "JUNGLE", "FIRST", myTeam, gameSet);
            if (Math.random() * 100 <= (double) redJngPlayerScore / 3) {
                detailedGanking(gameSet, false);
            }
        }
    }

    private int getMyLaneStatus(String position, MyTeam myTeam, GameSet gameSet) {
        int status;
        if (position.equals("JUNGLE")) {
            status = getMyDistinctPower("JUNGLING", position, "FIRST", myTeam, gameSet) * gameSet.getMyGold(position);
        } else {
            status = getMyDistinctPower("LANE", position, "FIRST", myTeam, gameSet) * gameSet.getMyGold(position);
        }
        return status;
    }

    private int getOpLaneStatus(String position, LeagueTeam oppositeTeam, GameSet gameSet) {
        int status;
        if (position.equals("JUNGLE")) {
            status =
                getOpDistinctPower("JUNGLING", position, "FIRST", oppositeTeam, gameSet) * gameSet.getOpGold(position);
        } else {
            status = getOpDistinctPower("LANE", position, "FIRST", oppositeTeam, gameSet) * gameSet.getOpGold(position);
        }
        return status;
    }

    private void detailedLane(GameSet gameSet, boolean isBlue, int level, String position) {
        if (level == 1) {
            gameSet.changeGold(isBlue, position, 400);
            gameSet.changeGold(!isBlue, position, 400);
        }
        if (level == 2) {
            gameSet.changeGold(isBlue, position, 500);
            gameSet.changeGold(!isBlue, position, 300);
            gameSet.destroyTower(isBlue, position, 100);
        } else {
            gameSet.changeGold(isBlue, position, 700);
            gameSet.changeGold(!isBlue, position, 300);
            gameSet.destroyTower(isBlue, position, 200);
        }
    }

    private void lane(MyTeam myTeam, LeagueTeam oppositeTeam, GameSet gameSet) {
        int bluePower;
        int redPower;
        String[] positions = {"TOP", "JUNGLE", "MIDDLE", "ADC"};
        for (String position : positions) {
            if (gameSet.isBlue()) {
                bluePower = getMyLaneStatus(position, myTeam, gameSet);
                redPower = getOpLaneStatus(position, oppositeTeam, gameSet);
            } else {
                bluePower = getOpLaneStatus(position, oppositeTeam, gameSet);
                redPower = getMyLaneStatus(position, myTeam, gameSet);
            }
            if (bluePower >= redPower) {
                float rate = (float) bluePower / redPower;
                if (rate >= 1 && rate < 1.5) {
                    detailedLane(gameSet, true, 1, position);
                } else if (rate >= 1.5 && rate < 2) {
                    detailedLane(gameSet, true, 2, position);
                } else {
                    detailedLane(gameSet, true, 3, position);
                }
            } else {
                float rate = (float) redPower / bluePower;
                if (rate >= 1 && rate < 1.5) {
                    detailedLane(gameSet, false, 1, position);
                } else if (rate >= 1.5 && rate < 2) {
                    detailedLane(gameSet, false, 2, position);
                } else {
                    detailedLane(gameSet, false, 3, position);
                }
            }

        }
    }

    private int getMyOperationPower(MyTeam myTeam, GameSet gameSet) {
        String status = "OPERATION";
        String time;
        if (gameSet.getCurTime() <= 15) {
            time = "FIRST";
        } else if (15 < gameSet.getCurTime() && gameSet.getCurTime() <= 35) {
            time = "MIDDLE";
        } else {
            time = "END";
        }
        String[] positions = {"TOP", "JUNGLE", "MIDDLE", "ADC", "SUPPORT"};

        int power = 0;
        for (String position : positions) {
            power += getMyDistinctPower(status, position, time, myTeam, gameSet);
        }
        power = power * gameSet.getMyGold();
        return power;
    }

    private int getOpOperationPower(LeagueTeam leagueTeam, GameSet gameSet) {
        String status = "OPERATION";
        String time;
        if (gameSet.getCurTime() <= 15) {
            time = "FIRST";
        } else if (15 < gameSet.getCurTime() && gameSet.getCurTime() <= 35) {
            time = "MIDDLE";
        } else {
            time = "END";
        }
        String[] positions = {"TOP", "JUNGLE", "MIDDLE", "ADC", "SUPPORT"};

        int power = 0;
        for (String position : positions) {
            power += getOpDistinctPower(status, position, time, leagueTeam, gameSet);
        }
        power = power * gameSet.getOpGold();
        return power;
    }


    private void detailedOperation(GameSet gameSet, boolean isBlue, int level) {
        if (level == 1) {
            gameSet.changeAllGold(isBlue, 400);
            gameSet.changeAllGold(!isBlue, 400);
            gameSet.destroyTower(isBlue, 200);
            gameSet.destroyTower(!isBlue, 200);
        }
        if (level == 2) {
            gameSet.changeAllGold(isBlue, 500);
            gameSet.changeAllGold(!isBlue, 300);
            gameSet.destroyTower(isBlue, 300);
            gameSet.destroyTower(!isBlue, 200);
        } else {
            gameSet.changeAllGold(isBlue, 700);
            gameSet.changeAllGold(!isBlue, 300);
            gameSet.destroyTower(isBlue, 400);
            gameSet.destroyTower(!isBlue, 200);
        }
    }

    private void operation(MyTeam myTeam, LeagueTeam oppositeTeam, GameSet gameSet) {
        int blueTeamOperationPower;
        int redTeamOperationPower;

        if (gameSet.isBlue()) {
            blueTeamOperationPower = getMyOperationPower(myTeam, gameSet);
            redTeamOperationPower = getOpOperationPower(oppositeTeam, gameSet);
        } else {
            blueTeamOperationPower = getOpOperationPower(oppositeTeam, gameSet);
            redTeamOperationPower = getMyOperationPower(myTeam, gameSet);
        }
        if (blueTeamOperationPower >= redTeamOperationPower) {
            float rate = (float) blueTeamOperationPower / redTeamOperationPower;
            if (rate >= 1 && rate < 1.5) {
                detailedOperation(gameSet, true, 1);
            } else if (rate >= 1.5 && rate < 2) {
                detailedOperation(gameSet, true, 2);
            } else {
                detailedOperation(gameSet, true, 3);
            }
        } else {
            float rate = (float) redTeamOperationPower / blueTeamOperationPower;
            if (rate >= 1 && rate < 1.5) {
                detailedOperation(gameSet, false, 1);
            } else if (rate >= 1.5 && rate < 2) {
                detailedOperation(gameSet, false, 2);
            } else {
                detailedOperation(gameSet, false, 3);
            }
        }

    }

    private int getMyFightPower(MyTeam myTeam, GameSet gameSet) {
        String status = "FIGHT";
        String time;
        if (gameSet.getCurTime() <= 15) {
            time = "FIRST";
        } else if (15 < gameSet.getCurTime() && gameSet.getCurTime() <= 35) {
            time = "MIDDLE";
        } else {
            time = "END";
        }
        String[] positions = {"TOP", "JUNGLE", "MIDDLE", "ADC", "SUPPORT"};

        int power = 0;
        for (String position : positions) {
            power += getMyDistinctPower(status, position, time, myTeam, gameSet);
        }
        power = power * gameSet.getMyGold();
        return power;
    }

    private int getOpFightPower(LeagueTeam leagueTeam, GameSet gameSet) {
        String status = "FIGHT";
        String time;
        if (gameSet.getCurTime() <= 15) {
            time = "FIRST";
        } else if (15 < gameSet.getCurTime() && gameSet.getCurTime() <= 35) {
            time = "MIDDLE";
        } else {
            time = "END";
        }
        String[] positions = {"TOP", "JUNGLE", "MIDDLE", "ADC", "SUPPORT"};

        int power = 0;
        for (String position : positions) {
            power += getOpDistinctPower(status, position, time, leagueTeam, gameSet);
        }
        power = power * gameSet.getMyGold();
        return power;
    }

    private void detailedFight(GameSet gameSet, boolean isBlue, int level) {
        if (level == 1) {
            gameSet.changeAllGold(isBlue, 500);
            gameSet.destroyTower(isBlue, 500);
            gameSet.changeAllGold(!isBlue, 300);
        } else {
            gameSet.changeAllGold(isBlue, 700);
            gameSet.destroyTower(isBlue, 1000);
            gameSet.changeAllGold(!isBlue, 300);
        }
    }

    private boolean fight(MyTeam myTeam, LeagueTeam oppositeTeam, GameSet gameSet) {
        int blueTeamFightnPower;
        int redTeamFightPower;

        if (gameSet.isBlue()) {
            blueTeamFightnPower = getMyFightPower(myTeam, gameSet);
            redTeamFightPower = getOpFightPower(oppositeTeam, gameSet);
        } else {
            blueTeamFightnPower = getOpFightPower(oppositeTeam, gameSet);
            redTeamFightPower = getMyFightPower(myTeam, gameSet);
        }
        float rate = (float) blueTeamFightnPower / (blueTeamFightnPower + redTeamFightPower);
        if (Math.random() < rate) {
            System.out.println("블루팀이 한타를 승리하였습니다.");
            if (blueTeamFightnPower < redTeamFightPower) {
                detailedFight(gameSet, true, 2);
            } else {
                detailedFight(gameSet, true, 1);
            }
            return true;
        } else {
            System.out.println("레드팀이 한타를 승리하였습니다.");
            if (blueTeamFightnPower > redTeamFightPower) {
                detailedFight(gameSet, false, 2);
            } else {
                detailedFight(gameSet, false, 1);
            }
            return false;
        }

    }

    private void addTime(GameSet gameSet) {
        gameSet.updateTime();
    }

    public void play() {
        //Set 생성
        MyTeam myTeam = myTeamRepository.findById(1L).orElseThrow();
        LeagueTeam oppositeTeam = leagueTeamRepository.findById(9L).orElseThrow();
        BaseTeam baseTeam = oppositeTeam.getBaseTeam();
//        GameSet gameSet = new GameSet();
//        gameSet.setCurTime(2);
//        gameSet.setBlueTopChamp(1L);
//        gameSet.setBlueJngChamp(1L);
//        gameSet.setBlueMidChamp(1L);
//        gameSet.setBlueAdcChamp(1L);
//        gameSet.setBlueSupChamp(1L);
//        gameSet.setRedTopChamp(1L);
//        gameSet.setRedJngChamp(1L);
//        gameSet.setRedMidChamp(1L);
//        gameSet.setRedAdcChamp(1L);
//        gameSet.setRedSupChamp(1L);
//        gameSetRepository.save(gameSet);
        GameSet gameSet = gameSetRepository.findById(15L).orElseThrow();
        int i = 0;
        while (true) {
            System.out.println("_______________________________________");
            System.out.println(gameSet.getCurTime());
            boolean blueWin = gameSet.isBlueWinable();
            boolean redWin = gameSet.isRedWinable();
            if (blueWin || redWin) {
                if (blueWin && redWin) {
                    if (fight(myTeam, oppositeTeam, gameSet)) {
                        System.out.println("블루팀이 승리하였습니다");

                    } else {
                        System.out.println("레드팀이 승리하였습니다");

                    }
                    break;
                }
                if (blueWin) {
                    if (fight(myTeam, oppositeTeam, gameSet)) {
                        System.out.println("블루팀이 승리하였습니다");
                        break;
                    }
                }
                if (redWin) {
                    if (!fight(myTeam, oppositeTeam, gameSet)) {
                        System.out.println("레드팀이 승리하였습니다");
                        break;
                    }
                }
                continue;
            }
            if (isBigBattle(gameSet)) {
                fight(myTeam, oppositeTeam, gameSet);
                addTime(gameSet);
                continue;
            } else {
                if (gameSet.getCurTime() <= 15) {
                    roaming(myTeam, oppositeTeam, gameSet);
                    ganking(myTeam, oppositeTeam, gameSet);
                    lane(myTeam, oppositeTeam, gameSet);
                } else {
                    operation(myTeam, oppositeTeam, gameSet);
                }
            }
            addTime(gameSet);
        }

    }

}
