package com.core.backend.domain.repository;

import com.core.backend.domain.BaseTeam;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface BaseTeamRepository extends JpaRepository<BaseTeam, Long> {
    @Query("select t from BaseTeam t join fetch t.players")
    List<BaseTeam> findAllFetchJoin();
}
