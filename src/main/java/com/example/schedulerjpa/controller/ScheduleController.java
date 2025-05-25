package com.example.schedulerjpa.controller;

import com.example.schedulerjpa.dto.ScheduleRequestDto;
import com.example.schedulerjpa.dto.ScheduleResponseDto;
import com.example.schedulerjpa.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping
    public ScheduleResponseDto create(@Valid @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.create(requestDto);
    }

    @GetMapping
    public List<ScheduleResponseDto> getAll() {
        return scheduleService.getAll();
    }

    @GetMapping("/{id}")
    public ScheduleResponseDto getOne(@PathVariable Long id) {
        return scheduleService.getById(id);
    }

    @PutMapping("/{id}")
    public ScheduleResponseDto update(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        scheduleService.delete(id);
    }
}
