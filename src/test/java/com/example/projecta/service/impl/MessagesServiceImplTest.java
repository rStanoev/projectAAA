package com.example.projecta.service.impl;

import com.example.projecta.domain.dto.binding.HardwareBindingModel;
import com.example.projecta.domain.dto.binding.MessagesBindingModel;
import com.example.projecta.domain.dto.entity.*;
import com.example.projecta.domain.dto.entity.enums.GenderEnum;
import com.example.projecta.domain.dto.entity.enums.HardwareEnum;
import com.example.projecta.domain.dto.entity.enums.UserRoles;
import com.example.projecta.repository.MessagesRepository;
import com.example.projecta.service.impl.CommentsHCServiceImpl;
import com.example.projecta.service.impl.MessagesServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MessagesServiceImplTest {

    @Mock
    private MessagesRepository mockMessagesRepository;

    @Mock
    private ModelMapper mockModelMapper;

    @Captor
    private ArgumentCaptor<Messages> messagesArgumentCaptor;


    private MessagesServiceImpl toTest;

    @BeforeEach
    void setUp() {
        toTest = new MessagesServiceImpl(
                mockMessagesRepository,
                mockModelMapper
                );
    }

    @Test
    void test_messages() {

        MessagesBindingModel messagesBindingModel = new MessagesBindingModel();
        messagesBindingModel.setText("gagaga");

        Role testRole = new Role();
        testRole.setId(1);
        testRole.setName(UserRoles.USER);
        Set<Role> roles = new HashSet<>();
        roles.add(testRole);

        Gender gender = new Gender();
        gender.setId(1L);
        gender.setType(GenderEnum.MALE);

        com.example.projecta.domain.dto.entity.User user = new com.example.projecta.domain.dto.entity.User();
        user.setId(1L);
        user.setFullName("Gosho");
        user.setUsername("gosho");
        user.setEmail("gosho@abv.bg");
        user.setPassword("goshoP");
        user.setGender(gender);
        user.setBorn(LocalDate.of(2001, 11, 26));
        user.setRoles(roles);

       Messages messages = new Messages();
        messages.setId(1L);
        messages.setText(messagesBindingModel.getText());
        messages.setReceiverId(2L);
        messages.setReceiverId(2L);
        messages.setTime(LocalTime.of(22, 34, 23));
        messages.setUser(user);

        when(mockModelMapper.map(messagesBindingModel, Messages.class)).thenReturn(messages);

        //ACT

        toTest.addMessage(messagesBindingModel, 1L, user);

        //ASSERT

        Mockito.verify(mockMessagesRepository).saveAndFlush(any());
    }
}
