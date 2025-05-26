package com.example.schedulerjpa.controller;

import com.example.schedulerjpa.dto.LoginRequestDto;
import com.example.schedulerjpa.dto.UserRequestDto;
import com.example.schedulerjpa.dto.UserResponseDto;
import com.example.schedulerjpa.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 사용자 controller 클래스
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public UserResponseDto signUp(@Valid @RequestBody UserRequestDto requestDto) {
        return userService.createUser(requestDto);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto requestDto,
                                        HttpServletRequest request) {
        userService.login(requestDto, request);
        return ResponseEntity.ok("로그인에 성공하였습니다.");
    }

    // 전체 사용자 조회
    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAllUsers();
    }

    // 선택 사용자 조회
    @GetMapping("/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        return userService.getUser(id);
    }

    // 사용자 정보 수정
    @PutMapping("/{id}")
    public UserResponseDto update(@PathVariable Long id, @RequestBody UserRequestDto requestDto) {
        return userService.updateUser(id, requestDto);
    }

    // 사용자 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("삭제되었습니다.");
    }
}
