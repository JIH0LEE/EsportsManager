package com.core.backend.service;

import com.core.backend.domain.GameMatch;
import com.core.backend.domain.League;
import com.core.backend.domain.repository.GameMatchRepository;
import com.core.backend.domain.repository.HeadCoachRepository;
import com.core.backend.domain.repository.LeagueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MatchService {

    private final LeagueRepository leagueRepository;
    private final GameMatchRepository gameMatchRepository;


    public void getMatchData(Long id){
        League league = leagueRepository.findLeagueByHeadCoach_IdAndFinishFalse(id).orElseThrow();
        GameMatch gameMatch = gameMatchRepository.findGameMatchByLeagueAndFinishIsFalse(league).orElse(null);
        //현재 리그의 팀 1과 팀2 데이터를 가져옴
        //Match 생성
        //Match data넘김
    }
}
