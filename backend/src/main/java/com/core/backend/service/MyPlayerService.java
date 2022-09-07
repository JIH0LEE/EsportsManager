package com.core.backend.service;

import com.core.backend.domain.MyPlayer;
import com.core.backend.domain.MyTeam;
import com.core.backend.domain.Player;
import com.core.backend.domain.enums.Position;
import com.core.backend.domain.repository.MyPlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MyPlayerService {

    private final MyPlayerRepository myPlayerRepository;


}
