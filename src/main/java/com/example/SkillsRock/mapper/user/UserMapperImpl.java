package com.example.SkillsRock.mapper.user;

import com.example.SkillsRock.dto.user.UserRequest;
import com.example.SkillsRock.dto.user.UserResponse;
import com.example.SkillsRock.entity.role.Role;
import com.example.SkillsRock.entity.user.User;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public User toEntityFromRequest(UserRequest request) {
        return User.createUser(request.getFirstName(),
                request.getLastName(),
                request.getPatronymic(),
                request.getPhoneNumber(),
                request.getAvatar());
    }

    @Override
    public UserResponse toResponseFromEntity(User user) {
        Set<String> roleNames = user.getRoles().stream()
                .map(Role::getRoleName)
                .collect(Collectors.toSet());

        return UserResponse.createUserResponse(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getPatronymic(),
                user.getPhoneNumber(),
                user.getAvatar(),
                roleNames);
    }
}
