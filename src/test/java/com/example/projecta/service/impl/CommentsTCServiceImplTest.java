package com.example.projecta.service.impl;

import com.example.projecta.domain.dto.binding.CommentsBindingModel;
import com.example.projecta.domain.dto.entity.*;
import com.example.projecta.domain.dto.entity.enums.ChairAndTableEnum;
import com.example.projecta.domain.dto.entity.enums.GenderEnum;
import com.example.projecta.domain.dto.entity.enums.PcAndLaptopEnum;
import com.example.projecta.domain.dto.entity.enums.UserRoles;
import com.example.projecta.repository.CommentsTCRepository;
import com.example.projecta.service.TandCPService;
import com.example.projecta.service.UserService;
import com.example.projecta.service.impl.CommentsPCServiceImpl;
import com.example.projecta.service.impl.CommentsTCServiceImpl;
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
public class CommentsTCServiceImplTest {

    @Mock
    private CommentsTCRepository mockCommentsTCRepository;

    @Mock
    private ModelMapper mockModelMapper;

    @Mock
    private TandCPService mockTandCPService;

    @Mock
    private UserService mockUserService;

    @Captor
    private ArgumentCaptor<CommentsTC> PcPArgumentCaptor;


    private CommentsTCServiceImpl toTest;

    @BeforeEach
    void setUp() {
        toTest = new CommentsTCServiceImpl(
                mockCommentsTCRepository,
                mockModelMapper,
                mockTandCPService,
                mockUserService);
    }

    @Test
    void test_addCommentTC() {

        TandC tandC = new TandC();
        tandC.setId(1L);
        tandC.setType(ChairAndTableEnum.TABLE);
        tandC.setDescription("dadad");

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

        TandCP tandCP = new TandCP();
        tandCP.setId(1L);
        tandCP.setName("intel");
        tandCP.setPrice(250.8);
        tandCP.setDescription("fafafa");
        tandCP.setTandC(tandC);

        CommentsTC commentsTC = new CommentsTC();
        commentsTC.setId(1L);
        commentsTC.setComment(commentsBindingModel.getComment());
        commentsTC.setUser(user);

        CommentsTC commentsTC1 = new CommentsTC();
        commentsTC1.setId(2L);
        commentsTC1.setComment(commentsBindingModel.getComment());
        commentsTC1.setUser(user);

        Set<CommentsTC> commentsTCSet = new HashSet<>();
        commentsTCSet.add(commentsTC);
        commentsTCSet.add(commentsTC1);

        tandCP.setCommentsTCSet(commentsTCSet);

        Principal principal = new Principal() {
            @Override
            public String getName() {
                return "gosho@abv.bg";
            }
        };

        when(mockTandCPService.getById3(1L)).thenReturn(tandCP);
        when(mockModelMapper.map(commentsBindingModel, CommentsTC.class)).thenReturn(commentsTC);


        //ACT

        toTest.addCommentTC(commentsBindingModel, 1L, principal);

        //ASSERT

        Assertions.assertEquals(2, tandCP.getCommentsTCSet().size());
    }


}
