package com.core.backend.service;

import com.core.backend.controller.dto.MyTeamResponse;
import com.core.backend.domain.repository.HeadCoachRepository;
import com.core.backend.domain.repository.MyPlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MyPlayerService {

    private final MyPlayerRepository myPlayerRepository;



}
