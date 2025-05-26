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

// 일정 service 클래스
@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    // 일정 생성
    public ScheduleResponseDto create(ScheduleRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다.")); // 식별자로 사용자를 찾을 수 없을 시 예외처리

        Schedule schedule = new Schedule(user, requestDto.getTitle(), requestDto.getContent());
        return new ScheduleResponseDto(scheduleRepository.save(schedule));
    }

    // 전체 일정 조회
    public List<ScheduleResponseDto> getAll() {
        return scheduleRepository.findAll().stream()
                .map(ScheduleResponseDto::new)
                .toList();
    }

    // 선택 일정 조회
    public ScheduleResponseDto getById(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("일정을 찾을 수 없습니다."));
        return new ScheduleResponseDto(schedule);
    }

    // 일정 수정
    public ScheduleResponseDto update(Long id, ScheduleRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("일정을 찾을 수 없습니다."));

        schedule.setTitle(requestDto.getTitle());
        schedule.setContent(requestDto.getContent());

        return new ScheduleResponseDto(scheduleRepository.save(schedule));
    }

    // 일정 삭제
    public void delete(Long id) {
        scheduleRepository.deleteById(id);
    }
}
