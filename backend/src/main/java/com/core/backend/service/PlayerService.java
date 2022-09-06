package com.core.backend.service;

import com.core.backend.controller.dto.PlayerResponse;
import com.core.backend.domain.Player;
import com.core.backend.domain.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;

    public List<PlayerResponse> getAll() {
        List<Player> playerList = playerRepository.findAll();
        return playerList.stream()
                .map(PlayerResponse::of)
                .collect(Collectors.toList());
    }

    public PlayerResponse getFaker() {
        Player faker = playerRepository.findPlayerByNickName("Faker");
        return PlayerResponse.of(faker);
    }
}
