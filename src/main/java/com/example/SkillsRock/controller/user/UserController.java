package com.example.SkillsRock.controller.user;

import com.example.SkillsRock.dto.user.UserRequest;
import com.example.SkillsRock.dto.user.UserResponse;
import com.example.SkillsRock.dto.user.UserUpdate;
import com.example.SkillsRock.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createNewUser")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@Valid @RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @GetMapping("/users")
    public UserResponse findUserById(@RequestParam UUID id) {
        return userService.findUserById(id);
    }

    @PutMapping("/userDetailsUpdate")
    public UserResponse updateUser(@Valid @RequestBody UserUpdate userUpdate) {
        return userService.updateUser(userUpdate);
    }

    @DeleteMapping("/users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@RequestParam UUID id) {
        userService.deleteUser(id);
    }
}
