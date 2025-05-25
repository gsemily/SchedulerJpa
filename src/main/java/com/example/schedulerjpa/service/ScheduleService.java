package com.example.schedulerjpa.service;

import com.example.schedulerjpa.dto.ScheduleRequestDto;
import com.example.schedulerjpa.dto.ScheduleResponseDto;
import com.example.schedulerjpa.entity.Schedule;
import com.example.schedulerjpa.entity.User;
import com.example.schedulerjpa.repository.ScheduleRepository;
import com.example.schedulerjpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public ScheduleResponseDto create(ScheduleRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        Schedule schedule = new Schedule(user, requestDto.getTitle(), requestDto.getContent());
        return new ScheduleResponseDto(scheduleRepository.save(schedule));
    }

    public List<ScheduleResponseDto> getAll() {
        return scheduleRepository.findAll().stream()
                .map(ScheduleResponseDto::new)
                .toList();
    }

    public ScheduleResponseDto getById(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("일정을 찾을 수 없습니다."));
        return new ScheduleResponseDto(schedule);
    }

    public ScheduleResponseDto update(Long id, ScheduleRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("일정을 찾을 수 없습니다."));

        schedule.setTitle(requestDto.getTitle());
        schedule.setContent(requestDto.getContent());

        return new ScheduleResponseDto(scheduleRepository.save(schedule));
    }

    public void delete(Long id) {
        scheduleRepository.deleteById(id);
    }
}
