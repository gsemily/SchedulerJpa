# Java Project - Scheduler(JPA)

## 💻프로젝트 소개
사용자의 일정을 관리하는 일정 관리 프로그램

### ⏰개발 기간
25.05.14 ~ 25.05.26

### ⚙️개발 환경
* Java 17
* JDK 1.8.0
* IDE : IntelliJ IDEA 2024.3.5
* Framework : SpringBoot
* Database : MySQL

## 📄프로젝트 구조
com.example.scheduler

├── config

│   └── FilterConfig.java

├── controller

│   ├── LoginController.java

│   ├── ScheduleController.java

│   └── UserController.java

├── dto

│   ├── LoginRequestDto.java

│   ├── LoginResponseDto.java

│   ├── ScheduleRequestDto.java

│   ├── ScheduleResponseDto.java

│   ├── UserRequestDto.java

│   └── UserResponseDto.java

├── entity

│   ├── BaseEntity.java

│   ├── Schedule.java

│   └── User.java

├── filter

│   └── LoginFilter.java

├── repository

│   ├── ScheduleRepository.java

│   └── UserRepository.java

├── service

│   ├── ScheduleService.java

│   └── UserService.java

└── SchedulerApplication.java

---
**API 설계**

![Image](https://github.com/user-attachments/assets/ab354b07-193f-4985-8ee1-e79c3c956ae8)

**ERD**

![Image](https://github.com/user-attachments/assets/c84baf82-e814-4e72-bfc6-03e1241fcc4e)

---
## 📌주요기능
**일정 작성하기**
* 사용자가 입력한 todo, writer, password 값을 받아 새로운 일정 생성
* created_at, modified_at은 현재 시간으로 자동 설정
* Entity → DTO로 변환 후 JSON으로 응답

**전체 일정 조회하기**
* 데이터베이스에 저장된 모든 일정 목록을 modified_at 기준 내림차순으로 정렬하여 반환

**선택 일정 조회하기**
* 고유 ID를 통해 특정 일정을 단건 조회
* 일정이 존재하지 않으면 에러 반환
* Entity를 DTO로 변환해 반환 (비밀번호는 포함하지 않음)

**일정 수정하기**
* 사용자가 요청한 ID와 함께 todo, writer, password 전달
* 비밀번호가 일치하는 경우만 수정 가능
* 수정 시점의 modified_at을 현재 시간으로 갱신

**일정 삭제하기**
* 삭제 요청 시, password를 요청 Body에 포함
* 비밀번호가 일치하는 경우 해당 일정 삭제 처리
* 비밀번호가 일치하지 않을 경우 에러 반환