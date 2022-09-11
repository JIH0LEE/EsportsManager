package com.core.backend.service;

import com.core.backend.controller.dto.MessageResponse;
import com.core.backend.controller.dto.MyTeamRequest;
import com.core.backend.controller.dto.TeamRankResponse;
import com.core.backend.domain.BaseTeam;
import com.core.backend.domain.HeadCoach;
import com.core.backend.domain.League;
import com.core.backend.domain.LeagueSchedule;
import com.core.backend.domain.LeagueTeam;
import com.core.backend.domain.MyPlayer;
import com.core.backend.domain.MyTeam;
import com.core.backend.domain.Player;
import com.core.backend.domain.enums.Condition;
import com.core.backend.domain.enums.Position;
import com.core.backend.domain.repository.BaseTeamRepository;
import com.core.backend.domain.repository.HeadCoachRepository;
import com.core.backend.domain.repository.LeagueRepository;
import com.core.backend.domain.repository.LeagueScheduleRepository;
import com.core.backend.domain.repository.LeagueTeamRepository;
import com.core.backend.domain.repository.MyPlayerRepository;
import com.core.backend.domain.repository.MyTeamRepository;
import com.core.backend.exception.NoLeague;
import com.core.backend.exception.NoValidHeadCoach;
import com.core.backend.exception.TeamNameExist;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LeagueService {

    private final MyTeamRepository myTeamRepository;
    private final BaseTeamRepository baseTeamRepository;
    private final MyPlayerRepository myPlayerRepository;
    private final HeadCoachRepository headCoachRepository;
    private final LeagueRepository leagueRepository;
    private final LeagueTeamRepository leagueTeamRepository;
    private final LeagueScheduleRepository leagueScheduleRepository;

    private void makeMyPlayer(MyTeam myTeam, List<Player> playerList, String position) {
        List<Player> players =
            playerList.stream()
                .filter(player -> player.getPosition() == Position.valueOf(position))
                .collect(Collectors.toList());
        Player main = players.get(0);
        myPlayerRepository.save(
            MyPlayer.builder()
                    .player(main)
                    .level(1)
                    .myTeam(myTeam)
                    .status(Condition.NORMAL)
                    .exp(0)
                    .position(position)
                    .build()
        );
        players.remove(0);
        players.stream()
            .forEach(player -> myPlayerRepository.save(
                MyPlayer.builder()
                        .player(player)
                        .level(1)
                        .myTeam(myTeam)
                        .position("SUB")
                        .status(Condition.NORMAL)
                        .exp(0)
                        .build())
            );
    }

    private League makeLeague(MyTeam myTeam, HeadCoach headCoach) {
        return leagueRepository.save(
            League.builder()
                .headCoach(headCoach)
                .myTeam(myTeam)
                .day(1)
                .finish(false)
                .build()
        );
    }

    private void makeLeagueTeams(League league, Long exceptId) {
        List<Long> baseTeamIds = new ArrayList(Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L));
        baseTeamIds.remove(exceptId);
        baseTeamIds.stream().forEach(baseTeamId -> leagueTeamRepository.save(
            LeagueTeam.builder()
                .baseTeam(baseTeamRepository.findById(baseTeamId).orElseThrow())
                .league(league)
                .build()));
    }

    private void findSameTeamName(String name){
        Optional<MyTeam> myTeam =myTeamRepository.findByName(name);
        if(myTeam.isPresent()){
            throw new TeamNameExist();
        }
    }
    public void makeMyLeague(MyTeamRequest myTeamRequest) {
        findSameTeamName(myTeamRequest.getName());
        BaseTeam baseTeam = baseTeamRepository.findById(myTeamRequest.getBaseTeamId())
            .orElseThrow();
        HeadCoach headCoach = headCoachRepository.findById(myTeamRequest.getHeadCoachId()).orElseThrow();
        List<Player> playerList = baseTeam.getPlayers();
        MyTeam myTeam = MyTeam.builder()
                .name(myTeamRequest.getName())
                .baseTeam(baseTeam)
                .headCoach(headCoach)
                .money(0)
                .build();
        myTeamRepository.save(myTeam);
        String[] positionArray = {"TOP", "JUNGLE", "MIDDLE", "ADC", "SUPPORT"};
        for (String position : positionArray) {
            makeMyPlayer(myTeam, playerList, position);
        }
        League league = makeLeague(myTeam, headCoach);
        makeLeagueTeams(league, myTeamRequest.getBaseTeamId());
    }

    public Optional<League> getLeagueInfo(Long id) {
        return leagueRepository.findLeagueByHeadCoachAndFinishFalse(headCoachRepository.findById(id).orElseThrow());
    }

    public List<TeamRankResponse> getRankingInfoByUser(Long id){
        HeadCoach headCoach = headCoachRepository.findById(id).orElseThrow(NoValidHeadCoach::new);
        League league = leagueRepository.findLeagueByHeadCoachAndFinishFalse(headCoach).orElseThrow(NoLeague::new);
        List<TeamRankResponse> teamRankResponseList = league.getLeagueTeamList().stream()
            .map(TeamRankResponse::of)
            .collect(Collectors.toList());
        teamRankResponseList.add(TeamRankResponse.of(league.getMyTeam()));
        Comparator<TeamRankResponse> compare = Comparator
            .comparing(TeamRankResponse::getMatchWin)
            .thenComparing(TeamRankResponse::getWinPoint).reversed();
        List<TeamRankResponse> sortedTeamRankResponseList = teamRankResponseList.stream()
            .sorted(compare)
            .collect(Collectors.toList());
        return sortedTeamRankResponseList;

    }

    private void addDay(League league){
        league.addDay();
    }


    private int getTeamPower(LeagueTeam leagueTeam){
        int power = 0;
        BaseTeam baseTeam = baseTeamRepository.findById(leagueTeam.getBaseTeam().getId()).orElseThrow();
        List<Player> playerList = baseTeam.getPlayers();
        for (Player player : playerList){
            power = power + player.getAllPower();
        }
        return power/playerList.size()-250;
    }

    private int getMyTeamPower(MyTeam myTeam){
        int power = 0;
        List<MyPlayer> myPlayerList = myTeam.getMyPlayerList();
        for (MyPlayer player : myPlayerList){
            power = power + player.getAllPower();
        }
        return power/myPlayerList.size()-250;
    }

    private void progressMyTeam(MyTeam myTeam,LeagueTeam leagueTeam,int teamNum){
        Integer team1Power;
        Integer team2Power;
        int score = 0;
        if (teamNum==1){
            team1Power = getMyTeamPower(myTeam);
            team2Power = getTeamPower(leagueTeam);
        }
        else{
            team2Power = getMyTeamPower(myTeam);
            team1Power = getTeamPower(leagueTeam);
        }
        float team1Rate = (float) (team1Power / (team1Power + team2Power));
        for(int i =0;i<3;i++){
            if(score==2 || score == -2){
                break;
            }
            double rand = Math.random();
            if(rand<=team1Rate){
                score+=1;
            }
            else{
                score-=1;
            }
        }
        if(teamNum==1){
            //1팀이 내 팀
           myTeam.updateWinPoint(score);
           leagueTeam.updateWinPoint(-score);
        }
        else{
            //2팀이 내 팀
            myTeam.updateWinPoint(-score);
            leagueTeam.updateWinPoint(score);
        }
    }
    private void progressOtherTeam(LeagueTeam leagueTeam1,LeagueTeam leagueTeam2){
        int team1Power;
        int team2Power;
        int score = 0;
        team1Power = getTeamPower(leagueTeam1);
        team2Power = getTeamPower(leagueTeam2);
        float team1Rate = ((float)team1Power / (team1Power + team2Power));
        for(int i =0;i<3;i++){
            if(score==2 || score == -2){
                break;
            }
            double rand = Math.random();

            if(rand<=team1Rate){
                score+=1;
            }
            else{
                score-=1;
            }
        }
        leagueTeam1.updateWinPoint(score);
        leagueTeam2.updateWinPoint(-score);
    }
    private void playGame(List<LeagueSchedule> leagueScheduleList,MyTeam myTeam,League league){
        for (LeagueSchedule leagueSchedule : leagueScheduleList ){
            if(leagueSchedule.getTeam1Id().equals(myTeam.getBaseTeam().getId())){
                //내 팀 대결
                BaseTeam baseTeam = baseTeamRepository.findById(leagueSchedule.getTeam2Id()).orElseThrow();
                LeagueTeam oppositeTeam = leagueTeamRepository.findLeagueTeamByLeagueAndBaseTeam(league,baseTeam);
                progressMyTeam(myTeam,oppositeTeam,1);
            }
            else if(leagueSchedule.getTeam2Id().equals(myTeam.getBaseTeam().getId())){

                BaseTeam baseTeam = baseTeamRepository.findById(leagueSchedule.getTeam1Id()).orElseThrow();
                LeagueTeam oppositeTeam = leagueTeamRepository.findLeagueTeamByLeagueAndBaseTeam(league,baseTeam);
                progressMyTeam(myTeam,oppositeTeam,2);
            }
            else{
                BaseTeam baseTeam1 = baseTeamRepository.findById(leagueSchedule.getTeam1Id()).orElseThrow();
                BaseTeam baseTeam2 = baseTeamRepository.findById(leagueSchedule.getTeam2Id()).orElseThrow();
                LeagueTeam leagueTeam1 = leagueTeamRepository.findLeagueTeamByLeagueAndBaseTeam(league,baseTeam1);
                LeagueTeam leagueTeam2 = leagueTeamRepository.findLeagueTeamByLeagueAndBaseTeam(league,baseTeam2);
                progressOtherTeam(leagueTeam1,leagueTeam2);
            }
        }
    }

    public MessageResponse progressLeague(Long id){
        for(int i =0; i<61;i++){
            HeadCoach headCoach = headCoachRepository.findById(id).orElseThrow();
            League league = leagueRepository.findLeagueByHeadCoachAndFinishFalse(headCoach).orElseThrow();
            MyTeam myTeam = myTeamRepository.findByHeadCoach(headCoach);
            List<LeagueSchedule> leagueScheduleList = leagueScheduleRepository.findAllByDay(league.getDay());
            Long baseTeamId = league.getMyTeam().getBaseTeam().getId();
            if(!leagueScheduleList.get(0).isGame()){
                addDay(league);
//            return new MessageResponse(true,"오늘 경기가 없습니다.");
            }
            else{
                playGame(leagueScheduleList,myTeam,league);
                addDay(league);
//            return new MessageResponse(true,"오늘 경기가 있습니다.");
            }
        }
        return new MessageResponse(true,"끝");
    }
    
    public void nextDay1(){
        //선수 스텟 업데이트
    }
}
