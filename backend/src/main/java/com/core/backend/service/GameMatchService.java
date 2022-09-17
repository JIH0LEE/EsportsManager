package com.core.backend.service;

import com.core.backend.controller.dto.GameMatchResponse;
import com.core.backend.domain.BaseTeam;
import com.core.backend.domain.GameMatch;
import com.core.backend.domain.HeadCoach;
import com.core.backend.domain.League;
import com.core.backend.domain.LeagueSchedule;
import com.core.backend.domain.MyTeam;
import com.core.backend.domain.repository.BaseTeamRepository;
import com.core.backend.domain.repository.GameMatchRepository;
import com.core.backend.domain.repository.HeadCoachRepository;
import com.core.backend.domain.repository.LeagueRepository;
import com.core.backend.domain.repository.LeagueScheduleRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GameMatchService {

    private final LeagueRepository leagueRepository;
    private final GameMatchRepository gameMatchRepository;
    private final LeagueScheduleRepository leagueScheduleRepository;
    private final HeadCoachRepository headCoachRepository;
    private final BaseTeamRepository baseTeamRepository;

    private Object[] findOppositeTeam(List<LeagueSchedule> leagueScheduleList, MyTeam myTeam) {
        for (LeagueSchedule leagueSchedule : leagueScheduleList) {
            if (leagueSchedule.getTeam1Id().equals(myTeam.getBaseTeam().getId())) {
                return new Object[]{true,
                    baseTeamRepository.findById(leagueSchedule.getTeam2Id()).orElseThrow()};
            } else if (leagueSchedule.getTeam2Id().equals(myTeam.getBaseTeam().getId())) {
                return new Object[]{false,
                    baseTeamRepository.findById(leagueSchedule.getTeam1Id()).orElseThrow()};
            }
        }
        return null;
    }

    private GameMatch makeNewMatch(League league, HeadCoach headCoach) {
        MyTeam myTeam = headCoach.getMyTeam();
        List<LeagueSchedule> leagueScheduleList = leagueScheduleRepository.findAllByDay(
            league.getDay());
        Object[] oppositeTeam = findOppositeTeam(leagueScheduleList, myTeam);
        GameMatch newGameMatch = GameMatch.builder().myTeam(myTeam)
            .oppositeTeam((BaseTeam) oppositeTeam[1])
            .finish(false)
            .gameSetCount(0)
            .gameScore(0)
            .isBlue((boolean) oppositeTeam[0])
            .league(league)
            .build();
        return gameMatchRepository.save(newGameMatch);
    }

    public GameMatchResponse getMatchData(Long id) {
        HeadCoach headCoach = headCoachRepository.findById(id).orElseThrow();
        League league = leagueRepository.findLeagueByHeadCoach_IdAndFinishFalse(id).orElseThrow();
        GameMatch gameMatch = gameMatchRepository.findGameMatchByLeagueAndFinishFalse(league)
            .orElse(null);
        if (gameMatch == null) {
            return GameMatchResponse.of(makeNewMatch(league, headCoach));
        }
        return GameMatchResponse.of(gameMatch);
    }
}
