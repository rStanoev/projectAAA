package com.example.projecta.repository;

import com.example.projecta.domain.dto.entity.CommentsTC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsTCRepository extends JpaRepository<CommentsTC, Long> {
}
