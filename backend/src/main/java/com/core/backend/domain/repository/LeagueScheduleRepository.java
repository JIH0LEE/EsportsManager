package com.core.backend.domain.repository;


import com.core.backend.domain.LeagueSchedule;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LeagueScheduleRepository extends JpaRepository<LeagueSchedule, Long> {
}
