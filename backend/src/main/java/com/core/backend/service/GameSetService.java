package com.core.backend.service;

import com.core.backend.controller.dto.BanpickRequest;
import com.core.backend.controller.dto.BanpickResponse;
import com.core.backend.controller.dto.GameSetResponse;
import com.core.backend.controller.dto.MessageResponse;
import com.core.backend.domain.*;
import com.core.backend.domain.enums.Position;
import com.core.backend.domain.repository.*;
import java.util.List;
import java.util.stream.Collectors;
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
    private final GameMatchRepository gameMatchRepository;

    public GameSetResponse getSetData(Long id){

        return makeGameSetResponse(gameSetRepository.findById(id).orElseThrow());
    }

    public GameSetResponse makeGameSetResponse(GameSet gameSet){
        List<Long> championList = gameSet.getChampList();
        List<String> championImageList = championList.stream()
            .map(champion->championRepository.findById(champion).orElseThrow().getImage())
            .collect(Collectors.toList());
        return GameSetResponse.of(gameSet,championImageList);
    }

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

    private String makeRoamingLog(String position,boolean isBlue,int num){
        String result="";
        if(isBlue){
            result+="블루팀의 ";
        }
        else{
            result+="레드팀의 ";
        }
        if(position.equals("MIDDLE")){
            result+="미드가 ";
        }
        else{
            result+="서포터가 ";
        }
        if(num==0){
            result+="탑 로밍에 성공하였습니다.";
        }
        else if(num==1){
            result+="바텀 로밍에 성공하였습니다.";
        }
        else{
            result+="미드 로밍에 성공하였습니다.";
        }
        result += ";";
        return result;
    }
    private String makeGankingLog(boolean isBlue,int num){
        String result="";
        if(isBlue){
            result+="블루팀이 ";
        }
        else{
            result+="레드팀이 ";
        }
        if(num==0){
            result+="탑 갱킹에 성공하였습니다.";
        }
        else if(num==1){
            result+="바텀 갱킹에 성공하였습니다.";
        }
        else{
            result+="미드 갱킹에 성공하였습니다.";
        }
        result += ";";
        return result;
    }
    private String makeLaneLog(String position,boolean isBlue,int num){
        String result="";
        if(isBlue){
            result+="블루팀의 ";
        }
        else{
            result+="레드팀의 ";
        }
        if(position.equals("TOP")){
            result+="탑이 ";
        }
        else if(position.equals("MIDDLE")){
            result+="미드가 ";
        }
        else if(position.equals("JUNGLE")){
            result+="정글이 ";
        }
        else{
            result+="바텀이 ";
        }
        if(num==2){
            result+="상대보다 성장이 더 우세합니다.";
        }
        else{
            result+="상대를 솔로킬을 달성하였습니다.";
        }

        result += ";";
        return result;
    }
    private String makeFightLog(boolean isBlue,int num){
        String result="";
        if(isBlue){
            result+="블루팀이 ";
        }
        else{
            result+="레드팀이 ";
        }

        if(num==1){
            result+="한타를 승리하였습니다.";
        }
        else{
            result+="한타를 압도적으로 승리하였습니다. 에이스!";
        }

        result += ";";
        return result;
    }
    private String makeTimeLog(GameSet gameSet){
        int min = gameSet.getCurTime()*60;
        int max = min + 120;
        int secondSum = (int) ((Math.random() * (max - min)) + min);
        String minute = Integer.toString(secondSum/60);
        String second = Integer.toString(secondSum%60);
        return minute+"분 "+second+"초/";
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
        String log = makeTimeLog(gameSet)+makeRoamingLog(position,isBlue,rand);
        System.out.println(log);
        gameSet.addLog(log);

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
        String log = makeTimeLog(gameSet)+makeGankingLog(isBlue,rand);
        System.out.println(log);
        gameSet.addLog(log);

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
        else if (level == 2) {
            gameSet.changeGold(isBlue, position, 500);
            gameSet.changeGold(!isBlue, position, 300);
            gameSet.destroyTower(isBlue, position, 100);
            String log = makeTimeLog(gameSet)+makeLaneLog(position,isBlue,level);
            System.out.println(log);
            gameSet.addLog(log);
        } else {
            gameSet.changeGold(isBlue, position, 700);
            gameSet.changeGold(!isBlue, position, 300);
            gameSet.destroyTower(isBlue, position, 200);
            String log = makeTimeLog(gameSet)+makeLaneLog(position,isBlue,level);
            System.out.println(log);
            gameSet.addLog(log);
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
                } else if (rate >= 1.5 && rate < 3) {
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
            if (rate >= 1 && rate < 1.1) {
                detailedOperation(gameSet, true, 1);
            } else if (rate >= 1.1 && rate < 1.4) {
                detailedOperation(gameSet, true, 2);
            } else {
                detailedOperation(gameSet, true, 3);
            }
        } else {
            float rate = (float) redTeamOperationPower / blueTeamOperationPower;
            if (rate >= 1 && rate < 1.1) {
                detailedOperation(gameSet, false, 1);
            } else if (rate >= 1.1 && rate < 1.4) {
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
        power = power * gameSet.getOpGold();
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
        String log = makeTimeLog(gameSet)+makeFightLog(isBlue,level);
        System.out.println(log);
        gameSet.addLog(log);
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

            if (blueTeamFightnPower < redTeamFightPower) {
                detailedFight(gameSet, true, 2);
            } else {
                detailedFight(gameSet, true, 1);
            }
            return true;
        } else {

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

    public GameSet play(Long id) {
        GameSet gameSet = gameSetRepository.findById(id).orElseThrow();
        MyTeam myTeam = myTeamRepository.findById(gameSet.getMyTeam()).orElseThrow();
        LeagueTeam oppositeTeam = leagueTeamRepository.findById(gameSet.getOppositeTeam()).orElseThrow();
        BaseTeam baseTeam = oppositeTeam.getBaseTeam();
        while (true) {
            System.out.println("_______________________________________");
            System.out.println(gameSet.getCurTime());
            boolean blueWin = gameSet.isBlueWinable();
            boolean redWin = gameSet.isRedWinable();
            if (blueWin || redWin) {
                if (blueWin && redWin) {
                    if (fight(myTeam, oppositeTeam, gameSet)) {
                        gameSet.getGameMatch().updateSetInfo(1);
                        String log ="블루팀이 승리하였습니다.";
                        gameSet.addLog(log);
                        System.out.println(log);

                    } else {
                        gameSet.getGameMatch().updateSetInfo(-1);
                        String log ="레드팀이 승리하였습니다.";
                        gameSet.addLog(log);
                        System.out.println(log);

                    }
                    gameSet.finishGame();
                    break;
                }
                if (blueWin) {
                    if (fight(myTeam, oppositeTeam, gameSet)) {
                        gameSet.getGameMatch().updateSetInfo(1);
                        String log ="블루팀이 승리하였습니다.";
                        gameSet.addLog(log);
                        System.out.println(log);
                        gameSet.finishGame();
                        break;
                    }
                }
                if (redWin) {
                    if (!fight(myTeam, oppositeTeam, gameSet)) {
                        gameSet.getGameMatch().updateSetInfo(-1);
                        String log = "레드팀이 승리하였습니다.";
                        System.out.println(log);
                        gameSet.addLog(log);
                        gameSet.finishGame();
                        break;
                    }
                }
                addTime(gameSet);
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
        return gameSet;
    }

    public BanpickResponse banpick(BanpickRequest banpickRequest){
        GameMatch gameMatch = gameMatchRepository.findById(banpickRequest.getMatchId()).orElseThrow();
        GameSet gameSet =GameSet.builder()
                .myTeam(banpickRequest.getMyTeam())
                .oppositeTeam(banpickRequest.getOppositeTeam())
                .blueTopChamp(banpickRequest.getBluePick().get(0))
                .blueJngChamp(banpickRequest.getBluePick().get(1))
                .blueMidChamp(banpickRequest.getBluePick().get(2))
                .blueAdcChamp(banpickRequest.getBluePick().get(3))
                .blueSupChamp(banpickRequest.getBluePick().get(4))
                .redTopChamp(banpickRequest.getRedPick().get(0))
                .redJngChamp(banpickRequest.getRedPick().get(1))
                .redMidChamp(banpickRequest.getRedPick().get(2))
                .redAdcChamp(banpickRequest.getRedPick().get(3))
                .redSupChamp(banpickRequest.getRedPick().get(4))
                .curTime(2)
                .isBlue(gameMatch.isBlue())
                .gameLog("")
                .finished(false)
                .gameMatch(gameMatch)
                .build();
        gameSetRepository.save(gameSet);
        return new BanpickResponse(gameSet.getId());
    }

}
