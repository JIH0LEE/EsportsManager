package com.core.backend.service;

import com.core.backend.controller.dto.MyTeamRequest;
import com.core.backend.controller.dto.TeamRankResponse;
import com.core.backend.domain.BaseTeam;
import com.core.backend.domain.HeadCoach;
import com.core.backend.domain.League;
import com.core.backend.domain.LeagueTeam;
import com.core.backend.domain.MyPlayer;
import com.core.backend.domain.MyTeam;
import com.core.backend.domain.Player;
import com.core.backend.domain.enums.Position;
import com.core.backend.domain.repository.BaseTeamRepository;
import com.core.backend.domain.repository.HeadCoachRepository;
import com.core.backend.domain.repository.LeagueRepository;
import com.core.backend.domain.repository.LeagueTeamRepository;
import com.core.backend.domain.repository.MyPlayerRepository;
import com.core.backend.domain.repository.MyTeamRepository;
import com.core.backend.exception.NoLeague;
import com.core.backend.exception.NoValidHeadCoach;
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
                    .build())
            );
    }

    private League makeLeague(MyTeam myTeam, HeadCoach headCoach) {
        return leagueRepository.save(
            League.builder()
                .headCoach(headCoach)
                .myTeam(myTeam)
                .day(0)
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

    public void makeMyLeague(MyTeamRequest myTeamRequest) {
        BaseTeam baseTeam = baseTeamRepository.findById(myTeamRequest.getBaseTeamId())
            .orElseThrow();
        HeadCoach headCoach = headCoachRepository.findById(myTeamRequest.getHeadCoachId()).orElseThrow();
        List<Player> playerList = baseTeam.getPlayers();
        MyTeam myTeam = MyTeam.builder()
            .name(myTeamRequest.getName())
            .baseTeam(baseTeam)
            .headCoach(headCoach)
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
            .comparing(TeamRankResponse::getWinPoint)
            .thenComparing(TeamRankResponse::getMatchWin).reversed();
        List<TeamRankResponse> sortedTeamRankResponseList = teamRankResponseList.stream()
            .sorted(compare)
            .collect(Collectors.toList());
        return sortedTeamRankResponseList;

    }
}
