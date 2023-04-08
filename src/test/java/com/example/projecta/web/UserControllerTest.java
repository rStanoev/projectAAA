package com.example.projecta.web;

import com.example.projecta.domain.dto.entity.Gender;
import com.example.projecta.domain.dto.entity.Role;
import com.example.projecta.domain.dto.entity.User;
import com.example.projecta.domain.dto.entity.enums.GenderEnum;
import com.example.projecta.domain.dto.entity.enums.UserRoles;
import com.example.projecta.repository.GenderRepository;
import com.example.projecta.repository.UserRepository;
import com.example.projecta.service.MessagesService;
import com.example.projecta.service.UserService;
import com.example.projecta.util.UserUtilTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import javax.servlet.http.Cookie;
import java.security.Principal;
import java.time.LocalDate;
import java.util.*;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService mockUserService;

    @Mock
    private ModelMapper mockModelMapper;

    @Mock
    private GenderRepository mockGenderRepository;

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private MessagesService mockMessagesService;

    @Mock
    Model model;

    @Mock
    Principal mockPrincipal = new Principal() {
        @Override
        public String getName() {
            return "gogo@abv.bg";
        }
    };


    @Mock
    private UserController toTest;



    @BeforeEach
    void setUp() {
        toTest = new UserController(
                mockUserService,
                mockModelMapper,
                mockGenderRepository,
                mockUserRepository,
                mockMessagesService);
    }

    @Test
    void testRegistrationPageShown() throws Exception {
        mockMvc.perform(get("/users/register")).
                andExpect(status().isOk()).
                andExpect(view().name("register"));
    }

    @Test
     void testUserRegistration() throws Exception {
        GenderEnum gender = GenderEnum.FEMALE;
        mockMvc.perform(post("/users/register").
                        param("fullName", "Anna Perrova").
                        param("username", "Petrova").
                        param("email", "anna@example.com").
                        param("password", "topsecret").
                        param("confirmPassword", "topsecret").
                        param("gender", String.valueOf(gender)).
                         param("born", String.valueOf(LocalDate.of(2001, 11, 26))).
                        cookie(new Cookie("lang", Locale.GERMAN.getLanguage())).
                        with(csrf())
                ).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/login"));


    }


    @Test
    void testAllUsersPageShown() throws Exception {


        mockMvc.perform(get("/users/all")).
                andExpect(status().isOk()).
                andExpect(view().name("listThatAreUsers"));
    }

    @Test
    void testProfileOFAnotherUserPageShown() throws Exception {

        Role testRole = new Role();
        testRole.setName(UserRoles.USER);
        Set<Role> roles = new HashSet<>();
        roles.add(testRole);

        Gender gender = new Gender();
        gender.setId(1L);
        gender.setType(GenderEnum.MALE);

        User user = new User();
        user.setId(1L);
        user.setUsername("gosho");
        user.setFullName("GEORGE");
        user.setPassword("11111");
        user.setEmail("gogo@abv.bg");
        user.setGender(gender);
        user.setRoles(roles);

        mockMvc.perform(get("/users/profile2/{name}", user.getUsername())).
                andExpect(status().isOk()).
                andExpect(view().name("profile"));

    }

    @Test
    void testChangeRolePageShown() throws Exception {

        Role testRole = new Role();
        testRole.setName(UserRoles.USER);
        Set<Role> roles = new HashSet<>();
        roles.add(testRole);

        Gender gender = new Gender();
        gender.setId(1L);
        gender.setType(GenderEnum.MALE);

        User user = new User();
        user.setId(1L);
        user.setUsername("gosho");
        user.setFullName("GEORGE");
        user.setPassword("11111");
        user.setEmail("gogo@abv.bg");
        user.setGender(gender);
        user.setRoles(roles);


        mockMvc.perform(get("/users/change/{name}", user.getUsername())).
                andExpect(status().isOk()).
                andExpect(view().name("listThatAreUsers"));

    }

   // @Test
   // void testShowAllUserPageShown() throws Exception {

     //   Role testRole = new Role();
     //  testRole.setName(UserRoles.USER);
      //  Set<Role> roles = new HashSet<>();
      //  roles.add(testRole);

      //  Gender gender = new Gender();
      //  gender.setId(1L);
      // gender.setType(GenderEnum.MALE);

       // Principal principal = new Principal() {
       //     @Override
         //   public String getName() {
         //       return "gogo@abv.bg";
         //   }
      //  };

     //  com.example.projecta.domain.dto.entity.User user = new com.example.projecta.domain.dto.entity.User();
     //   user.setId(1L);
      //  user.setFullName("Gosho");
      //  user.setUsername("gosho");
    //   user.setEmail("gogo@abv.bg");
    //  user.setPassword("goshoP");
    //  user.setGender(gender);
    //  user.setBorn(LocalDate.of(2001, 11, 26));
    //  user.setRoles(roles);



    // when(mockPrincipal.getName()).thenReturn("gogo@abv.bg");



    //  toTest.showAllUsers(mockPrincipal, model);

    // mockMvc.perform(get("/users/show/allUsers")).
    //         andExpect(status().isOk()).
    //        andExpect(view().name("listOfAllUsers"));

    //  }





}
