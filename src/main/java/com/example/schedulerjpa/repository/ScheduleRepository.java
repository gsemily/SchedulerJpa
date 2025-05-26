package com.example.schedulerjpa.repository;

import com.example.schedulerjpa.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

// JPA Repository를 상속하는 ScheduleRepository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
