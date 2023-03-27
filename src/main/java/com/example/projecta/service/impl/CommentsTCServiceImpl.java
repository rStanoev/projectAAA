package com.example.projecta.service.impl;

import com.example.projecta.domain.dto.binding.CommentsBindingModel;
import com.example.projecta.domain.dto.entity.CommentsTC;
import com.example.projecta.domain.dto.entity.TandCP;
import com.example.projecta.domain.dto.entity.User;
import com.example.projecta.repository.CommentsTCRepository;
import com.example.projecta.service.CommentsTCService;
import com.example.projecta.service.TandCPService;
import com.example.projecta.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class CommentsTCServiceImpl implements CommentsTCService {

    private final CommentsTCRepository commentsTCRepository;
    private final ModelMapper modelMapper;
    private final TandCPService tandCPService;

    private final UserService userService;

    public CommentsTCServiceImpl(CommentsTCRepository commentsTCRepository, ModelMapper modelMapper, TandCPService tandCPService, UserService userService) {
        this.commentsTCRepository = commentsTCRepository;
        this.modelMapper = modelMapper;
        this.tandCPService = tandCPService;
        this.userService = userService;
    }

    @Override
    public void addCommentTC(CommentsBindingModel commentsBindingModel, Long id, Principal principal) {
        TandCP tandCP = tandCPService.getById3(id);
        String username = principal.getName();
        User user = userService.getUser(username);
        Set<CommentsTC> commentsTCS = tandCP.getCommentsTCSet();

        CommentsTC commentsTC = modelMapper.map(commentsBindingModel, CommentsTC.class);
        commentsTC.setUser(user);

        commentsTCS.add(commentsTC);
        tandCP.setCommentsTCSet(commentsTCS);


        this.commentsTCRepository.saveAndFlush(commentsTC);
        this.tandCPService.map(tandCP);
    }

    @Override
    public Set<CommentsTC> getC(Long id) {
        TandCP tandCP = tandCPService.getById3(id);

        return tandCP.getCommentsTCSet();
    }
}
