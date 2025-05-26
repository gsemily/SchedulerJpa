# Java Project - Scheduler(JPA)

## 💻프로젝트 소개
사용자 인증 및 일정 관리를 지원하는 스프링부트 기반 웹 애플리케이션

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

│   ├── FilterConfig.java

│   └── PasswordEncoder.java

├── controller

│   ├── ScheduleController.java

│   └── UserController.java

├── dto

│   ├── LoginRequestDto.java

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
### API 설계

**사용자 기능**

![Image](https://github.com/user-attachments/assets/b2710669-fe6e-4983-b19e-d47588b32ca9)

**일정 기능**

![Image](https://github.com/user-attachments/assets/03cc9f38-a5fc-4fe5-b078-0e256c0e7685)

### ERD

![Image](https://github.com/user-attachments/assets/aa50cb98-a691-47f1-b71d-57e08f80d052)

---
## 📌주요기능

### 🧑‍💻 회원 기능

**회원가입**
* 유저명 4글자 이내, 이메일 형식 검증
* 비밀번호 숫자+문자 조합 검증
* 비밀번호 암호화 후 저장

**로그인**
* 이메일/비밀번호로 로그인
* 세션 생성 및 인증 필터 통과

### 📅 일정 관리

**일정 작성**
* 로그인된 사용자 한정 작성 가능
* 제목은 10자 이내 제한

**전체 일정 조회**
* 로그인된 사용자 한정 접근 가능

**특정 일정 조회**
* ID로 단건 조회

**일정 수정**
* 본인 소유 일정 수정 가능

**일정 삭제**
* 본인 소유 일정 삭제 가능

### 🔐 인증 및 보안

**로그인**
* 이메일과 비밀번호로 로그인
* 로그인 성공 시 Session 저장 (쿠키 자동 발급)
* 로그인 실패 시 HTTP Status code 401 반환

**필터 적용**

* 인증 제외 경로: /, /user/signup, /login 

  이외의 요청은 로그인 여부 검증

* 비밀번호 암호화 
* 회원가입 시 비밀번호 Bcrypt 해시 처리 
* 로그인 시 입력 비밀번호와 해시 비교