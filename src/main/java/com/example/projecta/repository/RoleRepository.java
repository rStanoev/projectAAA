package com.example.projecta.repository;

import com.example.projecta.domain.dto.entity.Role;
import com.example.projecta.domain.dto.entity.enums.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(UserRoles admin);
}
