package com.example.projecta.service.impl;

import com.example.projecta.domain.dto.binding.CommentsBindingModel;
import com.example.projecta.domain.dto.entity.*;
import com.example.projecta.domain.dto.entity.enums.GenderEnum;
import com.example.projecta.domain.dto.entity.enums.PcAndLaptopEnum;
import com.example.projecta.domain.dto.entity.enums.PeripheralEnum;
import com.example.projecta.domain.dto.entity.enums.UserRoles;
import com.example.projecta.repository.CommentsPCRepository;
import com.example.projecta.service.PcPService;
import com.example.projecta.service.UserService;
import com.example.projecta.service.impl.CommentsPCServiceImpl;
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
public class CommentsPCServiceImplTest {

    @Mock
    private CommentsPCRepository mockCommentsPCRepository;

    @Mock
    private ModelMapper mockModelMapper;

    @Mock
    private PcPService mockPcPService;

    @Mock
    private UserService mockUserService;

    @Captor
    private ArgumentCaptor<CommentsPC> PcPArgumentCaptor;


    private CommentsPCServiceImpl toTest;

    @BeforeEach
    void setUp() {
        toTest = new CommentsPCServiceImpl(
                mockCommentsPCRepository,
                mockModelMapper,
                mockPcPService,
                mockUserService);
    }

    @Test
    void test_addCommentPC() {

        Pc pc = new Pc();
        pc.setId(1L);
        pc.setType(PcAndLaptopEnum.PC);
        pc.setDescription("dadad");

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

        PcP pcP = new PcP();
        pcP.setId(1L);
        pcP.setName("intel");
        pcP.setPrice(250.8);
        pcP.setDescription("fafafa");
        pcP.setPc(pc);

        CommentsPC commentsPC = new CommentsPC();
        commentsPC.setId(1L);
        commentsPC.setComment(commentsBindingModel.getComment());
        commentsPC.setUser(user);

        CommentsPC commentsPC1 = new CommentsPC();
        commentsPC1.setId(2L);
        commentsPC1.setComment(commentsBindingModel.getComment());
        commentsPC1.setUser(user);

        Set<CommentsPC> commentsPCSet = new HashSet<>();
        commentsPCSet.add(commentsPC);
        commentsPCSet.add(commentsPC1);

        pcP.setCommentsPCSet(commentsPCSet);

        Principal principal = new Principal() {
            @Override
            public String getName() {
                return "gosho@abv.bg";
            }
        };

        when(mockPcPService.getById3(1L)).thenReturn(pcP);
        when(mockModelMapper.map(commentsBindingModel, CommentsPC.class)).thenReturn(commentsPC);


        //ACT

        toTest.addCommentPC(commentsBindingModel, 1L, principal);

        //ASSERT

        Assertions.assertEquals(2, pcP.getCommentsPCSet().size());
    }
}
