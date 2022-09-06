package com.core.backend.domain.repository;

import com.core.backend.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PlayerRepository extends JpaRepository<Player, Long> {
    Player findPlayerByNickName(String nickName);
}
