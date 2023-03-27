package com.example.projecta.repository;

import com.example.projecta.domain.dto.entity.PeripheralBought;
import com.example.projecta.domain.dto.entity.TandCBought;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TandCBoughtRepository extends JpaRepository<TandCBought, Long> {
}
