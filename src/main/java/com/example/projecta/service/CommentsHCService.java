package com.example.projecta.service;

import com.example.projecta.domain.dto.binding.CommentsBindingModel;
import com.example.projecta.domain.dto.entity.CommentsHC;

import java.security.Principal;
import java.util.Set;

public interface CommentsHCService {
    void addCommentHC(CommentsBindingModel commentsBindingModel, Long id, Principal principal);

    Set<CommentsHC> getC(Long id);
}
