package com.core.backend.domain.repository;

import com.core.backend.domain.HeadCoach;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeadCoachRepository extends JpaRepository<HeadCoach, Long> {

    Optional<HeadCoach> findByName(String name);
}
