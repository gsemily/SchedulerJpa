package com.example.schedulerjpa.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    private Long userId;
    private String title;
    private String content;
}
