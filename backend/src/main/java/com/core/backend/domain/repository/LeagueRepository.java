package com.core.backend.domain.repository;

import com.core.backend.domain.HeadCoach;
import com.core.backend.domain.League;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface LeagueRepository extends JpaRepository<League, Long> {
    Optional<League> findLeagueByHeadCoachAndFinishFalse(HeadCoach headCoach);
}
