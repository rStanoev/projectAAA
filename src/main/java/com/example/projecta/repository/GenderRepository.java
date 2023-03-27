package com.example.projecta.repository;

import com.example.projecta.domain.dto.entity.Gender;
import com.example.projecta.domain.dto.entity.enums.GenderEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepository extends JpaRepository<Gender, Long> {

    Gender findByType(GenderEnum male);
}
