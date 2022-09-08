package com.core.backend.domain.repository;

import com.core.backend.domain.Champion;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ChampionRepository extends JpaRepository<Champion, Long> {

}
