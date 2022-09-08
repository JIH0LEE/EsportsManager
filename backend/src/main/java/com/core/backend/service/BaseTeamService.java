package com.core.backend.service;

import com.core.backend.controller.dto.BaseTeamResponse;
import com.core.backend.domain.BaseTeam;
import com.core.backend.domain.repository.BaseTeamRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BaseTeamService {

    private final BaseTeamRepository baseTeamRepository;

    public List<BaseTeamResponse> getAll() {
        List<BaseTeam> championList = baseTeamRepository.findAll();
        return championList.stream()
            .map(BaseTeamResponse::of)
            .collect(Collectors.toList());
    }
}
