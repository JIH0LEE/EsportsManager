package com.core.backend.domain.repository;

import com.core.backend.domain.Enterprise;
import com.core.backend.domain.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SponsorRepository extends JpaRepository<Sponsor, Long> {

    List<Sponsor> findAllByWinGreaterThan(Integer win);
    List<Sponsor> findAllByWinLessThanEqual(Integer win);
    List<Sponsor> findAllByIdNotInAndWinGreaterThan(List<Long> ids,Integer win);
    List<Sponsor> findAllByIdNotInAndWinLessThanEqual(List<Long> ids,Integer win);



}
