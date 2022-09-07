package com.core.backend.service;

import com.core.backend.controller.dto.MyTeamResponse;
import com.core.backend.domain.HeadCoach;
import com.core.backend.domain.repository.HeadCoachRepository;
import com.core.backend.domain.repository.MyTeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MyTeamService {

    private final MyTeamRepository myTeamRepository;
    private final HeadCoachRepository headCoachRepository;

    public MyTeamResponse findTeamByUser(Long id){
        HeadCoach headCoach = headCoachRepository.findById(id).orElseThrow();
        return MyTeamResponse.of(myTeamRepository.findByHeadCoach(headCoach));
    }

}


