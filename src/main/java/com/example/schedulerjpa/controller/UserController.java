package com.example.schedulerjpa.controller;

import com.example.schedulerjpa.dto.UserRequestDto;
import com.example.schedulerjpa.dto.UserResponseDto;
import com.example.schedulerjpa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public UserResponseDto signUp(@RequestBody UserRequestDto requestDto) {
        return userService.signUp(requestDto);
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PutMapping("/{id}")
    public UserResponseDto update(@PathVariable Long id, @RequestBody UserRequestDto requestDto) {
        return userService.updateUser(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
