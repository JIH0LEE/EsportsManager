package com.core.backend.service;

import com.core.backend.controller.dto.ChangeEntryRequest;
import com.core.backend.controller.dto.MessageResponse;
import com.core.backend.controller.dto.MyTeamResponse;
import com.core.backend.domain.HeadCoach;
import com.core.backend.domain.MyPlayer;
import com.core.backend.domain.repository.HeadCoachRepository;
import com.core.backend.domain.repository.MyPlayerRepository;
import com.core.backend.domain.repository.MyTeamRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MyTeamService {

    private final MyTeamRepository myTeamRepository;

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

}


