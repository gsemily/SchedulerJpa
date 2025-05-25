package com.example.schedulerjpa.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    private Long userId;

    @Size(max = 10, message = "제목은 10자 이하여야 합니다.")
    private String title;

    private String content;
}
