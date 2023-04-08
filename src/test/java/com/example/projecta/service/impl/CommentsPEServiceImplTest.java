package com.example.projecta.service.impl;

import com.example.projecta.domain.dto.binding.CommentsBindingModel;
import com.example.projecta.domain.dto.entity.*;
import com.example.projecta.domain.dto.entity.enums.GenderEnum;
import com.example.projecta.domain.dto.entity.enums.HardwareEnum;
import com.example.projecta.domain.dto.entity.enums.PeripheralEnum;
import com.example.projecta.domain.dto.entity.enums.UserRoles;
import com.example.projecta.repository.CommentsPERepository;
import com.example.projecta.service.PeripheralPService;
import com.example.projecta.service.UserService;
import com.example.projecta.service.impl.CommentsHCServiceImpl;
import com.example.projecta.service.impl.CommentsPEServiceImpl;
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
public class CommentsPEServiceImplTest {

    @Mock
    private CommentsPERepository mockCommentsPERepository;

    @Mock
    private ModelMapper mockModelMapper;

    @Mock
    private PeripheralPService mockPeripheralPService;

    @Mock
    private UserService mockUserService;

    @Captor
    private ArgumentCaptor<CommentsPE> hardwarePArgumentCaptor;


    private CommentsPEServiceImpl toTest;

    @BeforeEach
    void setUp() {
        toTest = new CommentsPEServiceImpl(
                mockCommentsPERepository,
                mockModelMapper,
                mockPeripheralPService,
                mockUserService);
    }

    @Test
    void test_addCommentPE() {

        Peripheral peripheral = new Peripheral();
        peripheral.setId(1L);
        peripheral.setType(PeripheralEnum.CONTROLLER);
        peripheral.setDescription("dadad");

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

        PeripheralP peripheralP = new PeripheralP();
        peripheralP.setId(1L);
        peripheralP.setName("intel");
        peripheralP.setPrice(250.8);
        peripheralP.setDescription("fefeef");
        peripheralP.setPeripheral(peripheral);

        CommentsPE commentsPE = new CommentsPE();
        commentsPE.setId(1L);
        commentsPE.setComment(commentsBindingModel.getComment());
        commentsPE.setUser(user);

        CommentsPE commentsPE1 = new CommentsPE();
        commentsPE1.setId(2L);
        commentsPE1.setComment(commentsBindingModel.getComment());
        commentsPE1.setUser(user);

        Set<CommentsPE> commentsPESet = new HashSet<>();
        commentsPESet.add(commentsPE);
        commentsPESet.add(commentsPE1);

        peripheralP.setCommentsPESet(commentsPESet);

        Principal principal = new Principal() {
            @Override
            public String getName() {
                return "gosho@abv.bg";
            }
        };

        when(mockPeripheralPService.getById3(1L)).thenReturn(peripheralP);
        when(mockModelMapper.map(commentsBindingModel, CommentsPE.class)).thenReturn(commentsPE);


        //ACT

        toTest.addCommentPE(commentsBindingModel, 1L, principal);

        //ASSERT

        Assertions.assertEquals(2, peripheralP.getCommentsPESet().size());
    }
}
