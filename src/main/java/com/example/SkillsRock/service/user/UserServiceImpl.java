package com.example.SkillsRock.service.user;

import com.example.SkillsRock.dto.user.UserRequest;
import com.example.SkillsRock.dto.user.UserResponse;
import com.example.SkillsRock.dto.user.UserUpdate;
import com.example.SkillsRock.entity.role.Role;
import com.example.SkillsRock.entity.user.User;
import com.example.SkillsRock.exception.exception.BadRequestException;
import com.example.SkillsRock.exception.exception.NotFoundException;
import com.example.SkillsRock.mapper.user.UserMapper;
import com.example.SkillsRock.repository.role.RoleRepository;
import com.example.SkillsRock.repository.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public UserResponse createUser(UserRequest userRequest) {
        log.info("Creating new user with last name: {}", userRequest.getLastName());
        String lastName = userRequest.getLastName();

        if (userRepository.existsByLastName(lastName)) {
            log.warn("User with last name {} already exists", userRequest.getLastName());
            throw new BadRequestException(String.format("User %s name already exists", lastName));
        }

        User user = userMapper.toEntityFromRequest(userRequest);
        Role role = roleRepository.findByRoleName("USER");

        user.getRoles().add(role);

        user =  userRepository.save(user);

        log.info("Created new user with ID: {}", user.getId());
        return userMapper.toResponseFromEntity(user);
    }

    @Override
    @Cacheable(value = "users", key = "#id")
    public UserResponse findUserById(UUID id) {
        log.info("Fetching user by ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("User not found with ID: {}", id);
                    return new NotFoundException(String.format("User %s not found", id));
                });

        return userMapper.toResponseFromEntity(user);
    }

    @Override
    @Transactional
    @CachePut(value = "users", key = "#userUpdate.id")
    public UserResponse updateUser(UserUpdate userUpdate) {
        log.info("Updating user with ID: {}", userUpdate.getId());

        UUID id = userUpdate.getId();

        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("User not found for update with ID: {}", userUpdate.getId());
                    return new NotFoundException(String.format("User %s not found", id));
                });

        user.setFirstName(userUpdate.getFirstName());
        user.setLastName(userUpdate.getLastName());
        user.setPatronymic(userUpdate.getPatronymic());
        user.setPhoneNumber(userUpdate.getPhoneNumber());
        user.setAvatar(userUpdate.getAvatar());

        User updatedUser = userRepository.save(user);
        log.info("Updated user with ID: {}", updatedUser.getId());

        return userMapper.toResponseFromEntity(updatedUser);
    }

    @Override
    @Transactional
    @CacheEvict(value = "users", key = "#id")
    public void deleteUser(UUID id) {
        log.info("Deleting user with ID: {}", id);

        if (!userRepository.existsById(id)) {
            log.error("User not found for deletion with ID: {}", id);
            throw new NotFoundException(String.format("User with id=%s not found.", id));
        }
        userRepository.deleteById(id);
        log.info("Deleted user with ID: {}", id);
    }
}
