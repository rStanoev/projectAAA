package com.example.projecta.repository;

import com.example.projecta.domain.dto.entity.HardwareBought;
import com.example.projecta.domain.dto.entity.PeripheralBought;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HardwareBoughtRepository extends JpaRepository<HardwareBought, Long> {
}
