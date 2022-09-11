package com.core.backend.service;

import com.core.backend.controller.dto.*;
import com.core.backend.domain.*;
import com.core.backend.domain.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private final EnterpriseRepository enterpriseRepository;
    private final PersonalScheduleRepository personalScheduleRepository;

    private MyTeam findMyTeamByHeadCoachId(Long id){
        return myTeamRepository.findByHeadCoach(headCoachRepository.findById(id).orElseThrow());
    }

    public MyTeamResponse findTeamByUser(Long id) {
        HeadCoach headCoach = headCoachRepository.findById(id).orElseThrow();
        return MyTeamResponse.of(myTeamRepository.findByHeadCoach(headCoach));
    }

    public MessageResponse changeEntry(ChangeEntryRequest changeEntryRequest){
        String [] positionArray = {"TOP","JUNGLE","MIDDLE","ADC","SUPPORT","SUB"};
        List<Long> ids = changeEntryRequest.getMyPlayerList();
        System.out.println(ids.size());
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
        MyTeam myTeam = findMyTeamByHeadCoachId(id);
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

    public TeamEnterpriseResponse getEnterprises(Long id){
        MyTeam myTeam = findMyTeamByHeadCoachId(id);
        List<Long> ids = myTeam.getEnterprises();
        List<Enterprise> ing;
        List<Enterprise> yet;
        if(ids.size()==0){
            ing=new ArrayList<>();
            yet=enterpriseRepository.findAll();
        }
        else {
            ing = enterpriseRepository.findAllById(ids);
            yet=enterpriseRepository.findAllByIdNotIn(ids);
        }
        return new TeamEnterpriseResponse(
                ing.stream().map(EnterpriseResponse::of).collect(Collectors.toList()),
                yet.stream().map(EnterpriseResponse::of).collect(Collectors.toList())
        );
    }

    public MessageResponse contractSponsor(Long headCoachId,Long sponsorId){
        MyTeam myTeam = findMyTeamByHeadCoachId(headCoachId);
        myTeam.contractSponsor(sponsorId);
        return new MessageResponse(true,"성공적으로 계약이 되었습니다!");
    }

    public MessageResponse startEnterprise(Long headCoachId,Long enterpriseId){
        MyTeam myTeam = findMyTeamByHeadCoachId(headCoachId);
        myTeam.startEnterprise(enterpriseId);
        return new MessageResponse(true,"성공적으로 계약이 되었습니다!");
    }

    public MessageResponse applySchedule(PersonalScheduleListRequest personalScheduleRequestList){
        MyTeam myTeam = findMyTeamByHeadCoachId(personalScheduleRequestList.getHeadCoachId());
        personalScheduleRequestList.getPersonalScheduleRequestList().stream().forEach(
                personalScheduleRequest -> {
                    MyPlayer myPlayer = myPlayerRepository.findById(personalScheduleRequest.getId()).orElseThrow();
                    PersonalSchedule schedule =
                            personalScheduleRepository.findById(personalScheduleRequest.getScheduleId()).orElseThrow();
                    myTeam.changeMoney(schedule.getMoney());
                    myPlayer.applySchedule(schedule);
                }
        );
        return new MessageResponse(true,"컨디션이 적용되었습니다.");
    }

}


