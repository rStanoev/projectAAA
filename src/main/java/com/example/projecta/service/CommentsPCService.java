package com.example.projecta.service;

import com.example.projecta.domain.dto.binding.CommentsBindingModel;
import com.example.projecta.domain.dto.entity.CommentsPC;

import java.security.Principal;
import java.util.Set;

public interface CommentsPCService {
    void addCommentPC(CommentsBindingModel commentsBindingModel, Long id, Principal principal);

    Set<CommentsPC> getC(Long id);
}
