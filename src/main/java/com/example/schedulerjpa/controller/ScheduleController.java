package com.example.schedulerjpa.controller;

import com.example.schedulerjpa.dto.ScheduleRequestDto;
import com.example.schedulerjpa.dto.ScheduleResponseDto;
import com.example.schedulerjpa.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 일정 controller 클래스
@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    // 일정 생성
    @PostMapping
    public ScheduleResponseDto create(@Valid @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.create(requestDto);
    }

    // 전체 일정 조회
    @GetMapping
    public List<ScheduleResponseDto> getAll() {
        return scheduleService.getAll();
    }

    // 선택 일정 조회
    @GetMapping("/{id}")
    public ScheduleResponseDto getOne(@PathVariable Long id) {
        return scheduleService.getById(id);
    }

    // 일정 수정
    @PutMapping("/{id}")
    public ScheduleResponseDto update(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.update(id, requestDto);
    }

    // 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        scheduleService.delete(id);
        return ResponseEntity.ok("삭제되었습니다.");
    }
}
