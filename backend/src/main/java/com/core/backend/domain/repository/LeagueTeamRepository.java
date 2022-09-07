package com.core.backend.domain.repository;

import com.core.backend.domain.LeagueTeam;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LeagueTeamRepository extends JpaRepository<LeagueTeam, Long> {
}
