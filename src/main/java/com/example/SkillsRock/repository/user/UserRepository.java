package com.example.SkillsRock.repository.user;

import com.example.SkillsRock.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByLastName(String lastName);
}
