package com.core.backend.domain.repository;


import com.core.backend.domain.MyPlayer;
import com.core.backend.domain.MyTeam;
import com.core.backend.domain.enums.Position;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MyPlayerRepository extends JpaRepository<MyPlayer, Long> {
    MyPlayer findMyPlayerByMyTeamAndPosition(MyTeam myTeam, String position);
}
