package com.example.projecta.repository;

import com.example.projecta.domain.dto.entity.PeripheralP;
import com.example.projecta.domain.dto.entity.enums.PeripheralEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeripheralPRepository extends JpaRepository<PeripheralP, Long> {
    List<PeripheralP> findAllByPeripheral_Type(PeripheralEnum type);

    List<PeripheralP> findByOrderByPriceAsc();

    List<PeripheralP> findAllByOrderByPriceAsc();
}
