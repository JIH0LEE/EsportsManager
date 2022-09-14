package com.core.backend.domain.repository;

import com.core.backend.domain.BaseTeam;
import com.core.backend.domain.Player;
import com.core.backend.domain.enums.Position;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PlayerRepository extends JpaRepository<Player, Long> {

    Player findPlayerByNickName(String nickName);

    List<Player> findByBaseTeamAndPosition(BaseTeam baseTeam, Position position);
}
