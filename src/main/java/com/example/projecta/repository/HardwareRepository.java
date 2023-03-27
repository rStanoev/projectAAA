package com.example.projecta.repository;

import com.example.projecta.domain.dto.entity.Hardware;
import com.example.projecta.domain.dto.entity.enums.HardwareEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HardwareRepository extends JpaRepository<Hardware, Long> {
    Hardware findByType(HardwareEnum type);
}
