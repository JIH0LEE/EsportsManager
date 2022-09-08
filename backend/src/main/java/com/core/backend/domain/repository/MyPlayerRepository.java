package com.core.backend.domain.repository;


import com.core.backend.domain.MyPlayer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MyPlayerRepository extends JpaRepository<MyPlayer, Long> {

}
