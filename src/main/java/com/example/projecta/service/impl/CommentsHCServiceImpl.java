package com.example.projecta.service.impl;

import com.example.projecta.domain.dto.binding.CommentsBindingModel;
import com.example.projecta.domain.dto.entity.CommentsHC;
import com.example.projecta.domain.dto.entity.HardwareP;
import com.example.projecta.domain.dto.entity.User;
import com.example.projecta.repository.CommentsHCRepository;
import com.example.projecta.service.CommentsHCService;
import com.example.projecta.service.HardwarePService;
import com.example.projecta.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class CommentsHCServiceImpl implements CommentsHCService {


    private final CommentsHCRepository commentsHCRepository;
    private final HardwarePService hardwarePService;
    private final ModelMapper modelMapper;

    private final UserService userService;

    @Autowired
    public CommentsHCServiceImpl(CommentsHCRepository commentsHCRepository, HardwarePService hardwarePService, ModelMapper modelMapper, UserService userService) {
        this.commentsHCRepository = commentsHCRepository;
        this.hardwarePService = hardwarePService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public void addCommentHC(CommentsBindingModel commentsBindingModel, Long id, Principal principal) {
        HardwareP hardwareP = hardwarePService.getById3(id);
        String username = principal.getName();
        User user = userService.getUser(username);
        Set<CommentsHC> commentsHCSet = hardwareP.getCommentsHCSet();


        CommentsHC commentsHC = modelMapper.map(commentsBindingModel, CommentsHC.class);
        commentsHC.setUsers(user);

        commentsHCSet.add(commentsHC);
        hardwareP.setCommentsHCSet(commentsHCSet);


        this.commentsHCRepository.saveAndFlush(commentsHC);
        this.hardwarePService.map(hardwareP);
    }

    @Override
    public Set<CommentsHC> getC(Long id) {
        HardwareP hardwareP = hardwarePService.getById3(id);

        return hardwareP.getCommentsHCSet();
    }
}
