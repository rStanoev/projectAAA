package com.example.projecta.repository;

import com.example.projecta.domain.dto.entity.CommentsPE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsPERepository extends JpaRepository<CommentsPE, Long> {
}
