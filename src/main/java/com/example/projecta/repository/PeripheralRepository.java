package com.example.projecta.repository;

import com.example.projecta.domain.dto.entity.Peripheral;
import com.example.projecta.domain.dto.entity.enums.PeripheralEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeripheralRepository extends JpaRepository<Peripheral, Long> {
    Peripheral findByType(PeripheralEnum type);
}
