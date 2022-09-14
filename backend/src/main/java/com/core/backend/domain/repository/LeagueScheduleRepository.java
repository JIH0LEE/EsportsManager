package com.core.backend.domain.repository;


import com.core.backend.domain.LeagueSchedule;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LeagueScheduleRepository extends JpaRepository<LeagueSchedule, Long> {

    List<LeagueSchedule> findAllByDay(Integer day);
}
