package com.example.schedulerjpa.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;

// 일정 요청 DTO
@Getter
public class ScheduleRequestDto {
    private Long userId; //사용자 id

    @Size(max = 10, message = "제목은 10자 이하여야 합니다.") // 제목 입력 조건
    private String title; // 제목

    private String content; // 내용
}
