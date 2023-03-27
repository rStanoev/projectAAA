package com.example.projecta.repository;


import com.example.projecta.domain.dto.entity.CommentsPC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsPCRepository extends JpaRepository<CommentsPC, Long> {
}
