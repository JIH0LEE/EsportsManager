package com.core.backend.domain.repository;

import com.core.backend.domain.Enterprise;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {

    List<Enterprise> findAllByIdNotIn(List<Long> ids);

}
