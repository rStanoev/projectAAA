package com.example.projecta.service;

import com.example.projecta.domain.dto.binding.CommentsBindingModel;
import com.example.projecta.domain.dto.entity.CommentsTC;

import java.security.Principal;
import java.util.Set;

public interface CommentsTCService {
    void addCommentTC(CommentsBindingModel commentsBindingModel, Long id, Principal principal);

    Set<CommentsTC> getC(Long id);
}
