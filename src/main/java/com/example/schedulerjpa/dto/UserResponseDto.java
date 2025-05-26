package com.example.schedulerjpa.dto;

import com.example.schedulerjpa.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

// 사용자 응답 DTO
@Getter
public class UserResponseDto {
    private final Long id; // 식별자
    private final String username; // 사용자 이름
    private final String email; // 이메일

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm") // 시간 출력 형식 제한
    private final LocalDateTime createdAt; // 생성날짜

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private final LocalDateTime modifiedAt; // 수정날짜

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
        this.modifiedAt = user.getModifiedAt();
    }
}
