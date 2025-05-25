package com.example.schedulerjpa.dto;

import lombok.Getter;
import org.springframework.stereotype.Service;

@Getter
public class ScheduleRequestDto {
    private Long userId;
    private String title;
    private String content;
}
