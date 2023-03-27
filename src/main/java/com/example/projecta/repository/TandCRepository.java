package com.example.projecta.repository;

import com.example.projecta.domain.dto.entity.TandC;
import com.example.projecta.domain.dto.entity.enums.ChairAndTableEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TandCRepository extends JpaRepository<TandC, Long> {
    TandC findByType(ChairAndTableEnum type);
}
