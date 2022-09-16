package com.core.backend.domain.repository;

import com.core.backend.domain.BaseTeam;
import com.core.backend.domain.League;
import com.core.backend.domain.LeagueTeam;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LeagueTeamRepository extends JpaRepository<LeagueTeam, Long> {

    LeagueTeam findLeagueTeamByLeagueAndBaseTeam(League league, BaseTeam baseTeam);

    LeagueTeam findLeagueTeamByLeagueAndBaseTeam_Id(League league, Long baseTeamId);
}
