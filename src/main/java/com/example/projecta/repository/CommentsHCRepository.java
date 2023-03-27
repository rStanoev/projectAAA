package com.example.projecta.repository;

import com.example.projecta.domain.dto.entity.CommentsHC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsHCRepository extends JpaRepository<CommentsHC, Long> {
}
