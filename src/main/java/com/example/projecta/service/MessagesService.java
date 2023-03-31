package com.example.projecta.service;

import com.example.projecta.domain.dto.binding.MessagesBindingModel;
import com.example.projecta.domain.dto.entity.Messages;
import com.example.projecta.domain.dto.entity.User;

import java.util.List;

public interface MessagesService {
    List<Messages> findAll();


    void addMessage(MessagesBindingModel messagesBindingModel, Long id, User user);
}
