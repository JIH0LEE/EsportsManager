package com.core.backend.domain.repository;


import com.core.backend.domain.GameMatch;
import com.core.backend.domain.League;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GameMatchRepository extends JpaRepository<GameMatch, Long> {

    Optional<GameMatch> findGameMatchByLeagueAndFinishFalse(League league);

}
