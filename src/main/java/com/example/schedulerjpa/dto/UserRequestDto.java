package com.example.schedulerjpa.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

// 사용자 요청 DTO
@Getter
public class UserRequestDto {

    @Size(max = 4, message = "유저명은 4글자 이하여야 합니다.") // 크기 제한
    private String username; // 사용자 이름

    @Email(message = "이메일 형식을 지켜주세요.") // 이메일 형식 제한
    private String email; // 이메일

    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "비밀번호는 영문과 숫자를 포함해야 합니다.") // 형식 제한
    private String password; // 비밀번호
}
