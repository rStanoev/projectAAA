package com.example.projecta.service.impl;

import com.example.projecta.domain.dto.binding.CommentsBindingModel;
import com.example.projecta.domain.dto.entity.CommentsPC;
import com.example.projecta.domain.dto.entity.PcP;
import com.example.projecta.domain.dto.entity.User;
import com.example.projecta.repository.CommentsPCRepository;
import com.example.projecta.service.CommentsPCService;
import com.example.projecta.service.PcPService;
import com.example.projecta.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class CommentsPCServiceImpl implements CommentsPCService {

    private final CommentsPCRepository commentsPCRepository;
    private final ModelMapper modelMapper;
    private final PcPService pcPService;

    private final UserService userService;

    @Autowired
    public CommentsPCServiceImpl(CommentsPCRepository commentsPCRepository, ModelMapper modelMapper, PcPService pcPService, UserService userService) {
        this.commentsPCRepository = commentsPCRepository;
        this.modelMapper = modelMapper;
        this.pcPService = pcPService;
        this.userService = userService;
    }

    @Override
    public void addCommentPC(CommentsBindingModel commentsBindingModel, Long id, Principal principal) {
        PcP pcP = pcPService.getById3(id);
        String username = principal.getName();
        User user = userService.getUser(username);
        Set<CommentsPC> commentsPCS = pcP.getCommentsPCSet();

        CommentsPC commentsPC = modelMapper.map(commentsBindingModel, CommentsPC.class);
        commentsPC.setUser(user);

        commentsPCS.add(commentsPC);
        pcP.setCommentsPCSet(commentsPCS);


        this.commentsPCRepository.saveAndFlush(commentsPC);
        this.pcPService.map(pcP);
    }

    @Override
    public Set<CommentsPC> getC(Long id) {
        PcP pcP = pcPService.getById3(id);

        return pcP.getCommentsPCSet();
    }
}
