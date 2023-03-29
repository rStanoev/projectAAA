package com.example.projecta.repository;

import com.example.projecta.domain.dto.entity.HardwareP;
import com.example.projecta.domain.dto.entity.enums.HardwareEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface HardwarePRepository extends JpaRepository<HardwareP, Long> {
    List<HardwareP> findAllByHardware_Type(HardwareEnum type);

    List<HardwareP> findAllByOrderByPriceAsc();

}
