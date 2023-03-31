package com.example.projecta.service.impl;

import com.example.projecta.domain.dto.binding.MessagesBindingModel;
import com.example.projecta.domain.dto.entity.Messages;
import com.example.projecta.domain.dto.entity.User;
import com.example.projecta.repository.MessagesRepository;
import com.example.projecta.service.MessagesService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalTime;
import java.util.List;

@Service
public class MessagesServiceImpl implements MessagesService {

    private final MessagesRepository messagesRepository;
    private final ModelMapper modelMapper;


    public MessagesServiceImpl(MessagesRepository messagesRepository, ModelMapper modelMapper) {
        this.messagesRepository = messagesRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Messages> findAll() {
        return this.messagesRepository.findAll();
    }

    @Override
    public void addMessage(MessagesBindingModel messagesBindingModel, Long id, User user) {
        Messages messages = modelMapper.map(messagesBindingModel, Messages.class);
        messages.setTime(LocalTime.now());
        messages.setUser(user);
        messages.setReceiverId(id);

        this.messagesRepository.saveAndFlush(messages);
    }
}
