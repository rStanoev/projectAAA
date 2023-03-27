package com.example.projecta.repository;

import com.example.projecta.domain.dto.entity.Pc;
import com.example.projecta.domain.dto.entity.enums.PcAndLaptopEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PcRepository extends JpaRepository<Pc, Long> {
    Pc findByType(PcAndLaptopEnum type);
}
