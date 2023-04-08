package com.example.projecta.web;

import com.example.projecta.domain.dto.entity.Gender;
import com.example.projecta.domain.dto.entity.Role;
import com.example.projecta.domain.dto.entity.User;
import com.example.projecta.domain.dto.entity.enums.GenderEnum;
import com.example.projecta.domain.dto.entity.enums.UserRoles;
import com.example.projecta.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductsControllerTest {

    @Mock
    private UserRepository mockUserRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testAddPageShown() throws Exception {
        mockMvc.perform(get("/products/add")).
                andExpect(status().isOk()).
                andExpect(view().name("addButtons"));
    }

    @Test
    void testAddHCPageShown() throws Exception {
        mockMvc.perform(get("/products/addHC")).
                andExpect(status().isOk()).
                andExpect(view().name("add-hardware"));
    }

    @Test
    void testAddPEPageShown() throws Exception {
        mockMvc.perform(get("/products/addPE")).
                andExpect(status().isOk()).
                andExpect(view().name("add-peripheral"));
    }

    @Test
    void testAddPCPageShown() throws Exception {
        mockMvc.perform(get("/products/addPC")).
                andExpect(status().isOk()).
                andExpect(view().name("add-pc"));
    }
    @Test
    void testAddTCPageShown() throws Exception {
        mockMvc.perform(get("/products/addTC")).
                andExpect(status().isOk()).
                andExpect(view().name("add-TableAndChair"));
    }

   // @Test
    //  void showBought() throws Exception {

    //   Role testRole = new Role();
    //    testRole.setName(UserRoles.USER);
    //    Set<Role> roles = new HashSet<>();
    //   roles.add(testRole);

    //  Gender gender = new Gender();
    //   gender.setId(1L);
    //  gender.setType(GenderEnum.MALE);

    //  User user = new User();
    // user.setId(1L);
    // user.setUsername("gosho");
    //  user.setFullName("GEORGE");
    // user.setPassword("11111");
    //   user.setEmail("gogo@abv.bg");
    //   user.setGender(gender);
    //   user.setRoles(roles);

    //   when(mockUserRepository.findById(1L)).thenReturn(Optional.of(user));



    //  mockMvc.perform(get("/show/bought/{id}", user.getId())).
    //        andExpect(status().isOk()).
    //        andExpect(view().name("boughtrProducts"));
//

    //  }
}
