package com.example.SkillsRock.service.user;

import com.example.SkillsRock.dto.user.UserRequest;
import com.example.SkillsRock.dto.user.UserResponse;
import com.example.SkillsRock.dto.user.UserUpdate;

import java.util.UUID;

public interface UserService {

    UserResponse createUser(UserRequest userRequest);

    UserResponse findUserById(UUID id);

    UserResponse updateUser(UserUpdate userUpdate);

    void deleteUser(UUID id);
}
