package com.example.projecta.service;

import com.example.projecta.domain.dto.binding.CommentsBindingModel;
import com.example.projecta.domain.dto.entity.CommentsPE;

import java.security.Principal;
import java.util.Set;

public interface CommentsPEService {
    void addCommentPE(CommentsBindingModel commentsBindingModel, Long id, Principal principal);

    Set<CommentsPE> getC(Long id);
}
