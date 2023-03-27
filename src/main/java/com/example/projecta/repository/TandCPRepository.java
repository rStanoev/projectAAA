package com.example.projecta.repository;

import com.example.projecta.domain.dto.entity.TandCP;
import com.example.projecta.domain.dto.entity.enums.ChairAndTableEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TandCPRepository extends JpaRepository<TandCP, Long> {

    List<TandCP> findAllByTandC_Type(ChairAndTableEnum type);
}
