package com.core.backend.domain.repository;

import com.core.backend.domain.HeadCoach;
import com.core.backend.domain.MyTeam;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MyTeamRepository extends JpaRepository<MyTeam, Long> {

    MyTeam findByHeadCoach(HeadCoach headCoach);
}
