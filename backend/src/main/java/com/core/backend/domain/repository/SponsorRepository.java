package com.core.backend.domain.repository;

import com.core.backend.domain.Sponsor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SponsorRepository extends JpaRepository<Sponsor, Long> {

    List<Sponsor> findAllByWinGreaterThan(Integer win);

    List<Sponsor> findAllByWinLessThanEqual(Integer win);

    List<Sponsor> findAllByIdNotInAndWinGreaterThan(List<Long> ids, Integer win);

    List<Sponsor> findAllByIdNotInAndWinLessThanEqual(List<Long> ids, Integer win);


}
