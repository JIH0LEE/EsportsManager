package com.core.backend.domain.repository;

import com.core.backend.domain.BaseTeam;
import com.core.backend.domain.Player;
import com.core.backend.domain.enums.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PlayerRepository extends JpaRepository<Player, Long> {

    Player findPlayerByNickName(String nickName);

    List<Player> findByBaseTeamAndPosition(BaseTeam baseTeam, Position position);
}
