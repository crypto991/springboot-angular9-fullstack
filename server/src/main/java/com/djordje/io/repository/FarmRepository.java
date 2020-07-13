package com.djordje.io.repository;

import com.djordje.io.entity.Farm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FarmRepository extends JpaRepository<Farm, Long> {

    List<Farm> getAllByAccount_AccountId(String id);

    Farm findByFarmId(String farmId);

    void deleteByFarmId(String farmId);

}
