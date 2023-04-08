package com.example.projecta.util;

import com.example.projecta.domain.dto.entity.Gender;
import com.example.projecta.domain.dto.entity.Role;
import com.example.projecta.domain.dto.entity.User;
import com.example.projecta.domain.dto.entity.enums.GenderEnum;
import com.example.projecta.domain.dto.entity.enums.UserRoles;
import com.example.projecta.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserUtilTest {

    private UserRepository userRepository;

    public UserUtilTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createTestUser() {

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

        return userRepository.saveAndFlush(user);
    }
}
