package com.core.backend.service;

import com.core.backend.controller.dto.*;
import com.core.backend.domain.HeadCoach;
import com.core.backend.domain.MyPlayer;
import com.core.backend.domain.MyTeam;
import com.core.backend.domain.Sponsor;
import com.core.backend.domain.repository.HeadCoachRepository;
import com.core.backend.domain.repository.MyPlayerRepository;
import com.core.backend.domain.repository.MyTeamRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.core.backend.domain.repository.SponsorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MyTeamService {

    private final MyTeamRepository myTeamRepository;
    private final SponsorRepository sponsorRepository;
    private final MyPlayerRepository myPlayerRepository;
    private final HeadCoachRepository headCoachRepository;

    public MyTeamResponse findTeamByUser(Long id) {
        HeadCoach headCoach = headCoachRepository.findById(id).orElseThrow();
        return MyTeamResponse.of(myTeamRepository.findByHeadCoach(headCoach));
    }

    public MessageResponse changeEntry(ChangeEntryRequest changeEntryRequest){
        String [] positionArray = {"TOP","JUNGLE","MIDDLE","ADC","SUPPORT","SUB"};
        List<Long> ids = changeEntryRequest.getMyPlayerList();
        for(int i = 0; i < ids.size();i++){
            String position;
            if(i>=5){
                position = positionArray[5];
            }
            else {
                position = positionArray[i];
            }
            MyPlayer myPlayer =myPlayerRepository.findById(ids.get(i)).orElseThrow();
            myPlayer.update(position);
        }
        return new MessageResponse( true,"엔트리가 변경되었습니다.");
    }

    public TeamSponsorResponse getSponsors(Long id){
        HeadCoach headCoach = headCoachRepository.findById(id).orElseThrow();
        MyTeam myTeam = myTeamRepository.findByHeadCoach(headCoach);
        List<Long> ids = myTeam.getSponsors();
        List<Sponsor> alreadySponsor;
        List<Sponsor> disableSponsor;
        List<Sponsor> enableSponsor;
        if(ids.size()==0){
            alreadySponsor=new ArrayList<>();
            disableSponsor=sponsorRepository.findAllByWinGreaterThan(myTeam.getMatchWin());
            enableSponsor = sponsorRepository.findAllByWinLessThanEqual(myTeam.getMatchWin());
        }
        else {
            alreadySponsor = sponsorRepository.findAllById(ids);
            disableSponsor=sponsorRepository.findAllByIdNotInAndWinGreaterThan(ids,myTeam.getMatchWin());
            enableSponsor = sponsorRepository.findAllByIdNotInAndWinLessThanEqual(ids,myTeam.getMatchWin());
        }

        return new TeamSponsorResponse(
                alreadySponsor.stream().map(SponsorResponse::of).collect(Collectors.toList()),
                disableSponsor.stream().map(SponsorResponse::of).collect(Collectors.toList()),
                enableSponsor.stream().map(SponsorResponse::of).collect(Collectors.toList())
        );
    }

}


