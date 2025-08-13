package com.example.SkillsRock.mapper.user;

import com.example.SkillsRock.dto.user.UserRequest;
import com.example.SkillsRock.dto.user.UserResponse;
import com.example.SkillsRock.entity.user.User;

public interface UserMapper {
    User toEntityFromRequest(UserRequest request);

    UserResponse toResponseFromEntity(User user);
}
