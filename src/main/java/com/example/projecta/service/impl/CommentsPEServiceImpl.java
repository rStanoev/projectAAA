package com.example.projecta.service.impl;

import com.example.projecta.domain.dto.binding.CommentsBindingModel;
import com.example.projecta.domain.dto.entity.CommentsPE;
import com.example.projecta.domain.dto.entity.PeripheralP;
import com.example.projecta.domain.dto.entity.User;
import com.example.projecta.repository.CommentsPERepository;
import com.example.projecta.service.CommentsPEService;
import com.example.projecta.service.PeripheralPService;
import com.example.projecta.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class CommentsPEServiceImpl implements CommentsPEService {

    private final CommentsPERepository commentsPERepository;
    private final ModelMapper modelMapper;
    private final PeripheralPService peripheralPService;

    private final UserService userService;

    @Autowired
    public CommentsPEServiceImpl(CommentsPERepository commentsPERepository, ModelMapper modelMapper, PeripheralPService peripheralPService, UserService userService) {
        this.commentsPERepository = commentsPERepository;
        this.modelMapper = modelMapper;
        this.peripheralPService = peripheralPService;
        this.userService = userService;
    }

    @Override
    public void addCommentPE(CommentsBindingModel commentsBindingModel, Long id, Principal principal) {
        PeripheralP peripheralP = peripheralPService.getById3(id);
        String username = principal.getName();
        User user = userService.getUser(username);
        Set<CommentsPE> commentsPES = peripheralP.getCommentsPESet();

        CommentsPE commentsPE = modelMapper.map(commentsBindingModel, CommentsPE.class);
        commentsPE.setUser(user);

        commentsPES.add(commentsPE);
        peripheralP.setCommentsPESet(commentsPES);



        this.commentsPERepository.saveAndFlush(commentsPE);
        this.peripheralPService.map(peripheralP);
    }

    @Override
    public Set<CommentsPE> getC(Long id) {
        PeripheralP peripheralP = peripheralPService.getById3(id);

        return peripheralP.getCommentsPESet();
    }
}
