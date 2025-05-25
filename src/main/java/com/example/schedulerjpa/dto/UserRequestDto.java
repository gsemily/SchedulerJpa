package com.example.schedulerjpa.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class UserRequestDto {

    @Size(max = 4, message = "유저명은 4글자 이하여야 합니다.")
    private String username;

    @Email(message = "이메일 형식을 지켜주세요.")
    private String email;

    @Pattern(regexp = "^[a-zA-Z0-9]{4,}$", message = "비밀번호는 영문과 숫자를 포함해 4자 이상이어야 합니다.")
    private String password;
}
