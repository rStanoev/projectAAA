package com.example.projecta.repository;

import com.example.projecta.domain.dto.entity.PcP;
import com.example.projecta.domain.dto.entity.enums.PcAndLaptopEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PcPRepository extends JpaRepository<PcP, Long> {
    List<PcP> findAllByPc_Type(PcAndLaptopEnum type);

    List<PcP> findByOrderByPriceAsc();


    List<PcP> findAllByOrderByPriceAsc();
}
