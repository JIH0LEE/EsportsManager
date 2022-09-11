package com.core.backend.domain.repository;

import com.core.backend.domain.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {

    List<Enterprise> findAllByIdNotIn(List<Long> ids);

}
