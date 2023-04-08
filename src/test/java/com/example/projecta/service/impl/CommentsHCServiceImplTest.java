package com.example.projecta.service.impl;

import com.example.projecta.domain.dto.binding.CommentsBindingModel;
import com.example.projecta.domain.dto.entity.*;
import com.example.projecta.domain.dto.entity.enums.GenderEnum;
import com.example.projecta.domain.dto.entity.enums.HardwareEnum;
import com.example.projecta.domain.dto.entity.enums.UserRoles;
import com.example.projecta.domain.dto.model.HardwarePListModel;
import com.example.projecta.repository.CommentsHCRepository;
import com.example.projecta.service.HardwarePService;
import com.example.projecta.service.UserService;
import com.example.projecta.service.impl.CommentsHCServiceImpl;
import com.example.projecta.service.impl.CommentsPCServiceImpl;
import com.example.projecta.service.impl.HardwarePServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.security.Principal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommentsHCServiceImplTest {

    @Mock
    private CommentsHCRepository mockCommentsHCRepository;

    @Mock
    private HardwarePService mockHardwarePService;

    @Mock
    private ModelMapper mockModelMapper;

    @Mock
    private UserService mockUserService;

    @Captor
    private ArgumentCaptor<CommentsHC> hardwarePArgumentCaptor;


    private CommentsHCServiceImpl toTest;

    @BeforeEach
    void setUp() {
        toTest = new CommentsHCServiceImpl(
                mockCommentsHCRepository,
                mockHardwarePService,
                mockModelMapper,
                mockUserService);
    }

    @Test
    void test_addCommentHC() {

        Hardware hardware = new Hardware();
        hardware.setId(1L);
        hardware.setType(HardwareEnum.CPU);
        hardware.setDescription("dadad");

        Role testRole = new Role();
        testRole.setId(1);
        testRole.setName(UserRoles.USER);
        Set<Role> roles = new HashSet<>();
        roles.add(testRole);

        Gender gender = new Gender();
        gender.setId(1L);
        gender.setType(GenderEnum.MALE);


        com.example.projecta.domain.dto.entity.User user = new com.example.projecta.domain.dto.entity.User();user.setId(1L);
        user.setFullName("Gosho");
        user.setUsername("gosho");
        user.setEmail("gosho@abv.bg");
        user.setPassword("goshoP");
        user.setGender(gender);
        user.setBorn(LocalDate.of(2001, 11, 26));
        user.setRoles(roles);

        CommentsBindingModel commentsBindingModel = new CommentsBindingModel();
        commentsBindingModel.setComment("dada");

        HardwareP hardwareP = new HardwareP();
        hardwareP.setId(1L);
        hardwareP.setName("intel");
        hardwareP.setPrice(250.8);
        hardwareP.setDescription("fefeef");
        hardwareP.setHardware(hardware);

        CommentsHC commentsHC = new CommentsHC();
        commentsHC.setId(1L);
        commentsHC.setComment(commentsBindingModel.getComment());
        commentsHC.setUsers(user);

        CommentsHC commentsHC1 = new CommentsHC();
        commentsHC1.setId(2L);
        commentsHC1.setComment(commentsBindingModel.getComment());
        commentsHC1.setUsers(user);

        Set<CommentsHC> commentsHCSet = new HashSet<>();
        commentsHCSet.add(commentsHC);
        commentsHCSet.add(commentsHC1);

        hardwareP.setCommentsHCSet(commentsHCSet);

        Principal principal = new Principal() {
            @Override
            public String getName() {
                return "gosho@abv.bg";
            }
        };

        when(mockHardwarePService.getById3(1L)).thenReturn(hardwareP);
        when(mockModelMapper.map(commentsBindingModel, CommentsHC.class)).thenReturn(commentsHC);


        //ACT

        toTest.addCommentHC(commentsBindingModel, 1L, principal);

        //ASSERT

        Assertions.assertEquals(2, hardwareP.getCommentsHCSet().size());
    }
}
