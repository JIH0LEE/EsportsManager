package com.core.backend.domain.repository;

import com.core.backend.domain.HeadCoach;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeadCoachRepository extends JpaRepository<HeadCoach, Long> {
    Optional<HeadCoach> findByName(String name);
}
