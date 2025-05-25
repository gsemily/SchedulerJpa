package com.example.schedulerjpa.controller;

import com.example.schedulerjpa.dto.LoginRequestDto;
import com.example.schedulerjpa.dto.UserRequestDto;
import com.example.schedulerjpa.dto.UserResponseDto;
import com.example.schedulerjpa.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public UserResponseDto signUp(@RequestBody UserRequestDto requestDto) {
        return userService.createUser(requestDto);
    }

    @PostMapping("/login")
    public void login(@RequestBody LoginRequestDto requestDto,
                      HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        userService.login(requestDto, request, response);
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
