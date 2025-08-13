package com.example.SkillsRock.repository.role;

import com.example.SkillsRock.entity.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Role findByRoleName(String roleName);
}
