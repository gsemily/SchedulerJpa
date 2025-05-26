package com.example.schedulerjpa.dto;

import lombok.Getter;

// 로그인 요청 DTO
@Getter
public class LoginRequestDto {
    private String email; // 이메일
    private String password; // 비밀번호
}
