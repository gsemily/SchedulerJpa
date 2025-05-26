package com.example.schedulerjpa.dto;

import com.example.schedulerjpa.entity.Schedule;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

// 일정 응답 DTO
@Getter
public class ScheduleResponseDto {
    private final Long id; // 식별자
    private final String username; // 사용자 이름
    private final String title; // 제목
    private final String content; // 내용

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm") // 연-월-일-시-분 형식으로 시간 출력
    private final LocalDateTime createdAt; // 생성날짜

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private final LocalDateTime modifiedAt; // 수정날짜

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.username = schedule.getUser().getUsername();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
    }
}
