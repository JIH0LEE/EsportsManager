package com.core.backend.domain.repository;

import com.core.backend.domain.HeadCoach;
import com.core.backend.domain.League;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LeagueRepository extends JpaRepository<League, Long> {

    Optional<League> findLeagueByHeadCoachAndFinishFalse(HeadCoach headCoach);

    Optional<League> findLeagueByHeadCoach_IdAndFinishFalse(Long id);
}
