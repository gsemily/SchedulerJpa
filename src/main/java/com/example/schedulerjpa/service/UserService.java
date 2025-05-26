package com.example.schedulerjpa.service;

import com.example.schedulerjpa.config.PasswordEncoder;
import com.example.schedulerjpa.dto.LoginRequestDto;
import com.example.schedulerjpa.dto.UserRequestDto;
import com.example.schedulerjpa.dto.UserResponseDto;
import com.example.schedulerjpa.entity.User;
import com.example.schedulerjpa.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

// 사용자 service 클래스
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 사용자 생성
    public UserResponseDto createUser(UserRequestDto requestDto) {
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
        User user = new User(requestDto.getUsername(), requestDto.getEmail(), encodedPassword);
        return new UserResponseDto(userRepository.save(user));
    }

    // 로그인
    public void login(LoginRequestDto requestDto, HttpServletRequest request) {
        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "등록된 사용자가 없습니다.")); // 401 error

        // 암호화된 비밀번호 비교
        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다."); // 401 error
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("user", user);
    }

    // 전체 사용자 조회
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserResponseDto::new).toList();
    }

    // 선택 사용자 조회
    public UserResponseDto getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));
        return new UserResponseDto(user);
    }

    // 사용자 정보 수정
    public UserResponseDto updateUser(Long id, UserRequestDto requestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));
        user.update(requestDto.getUsername(), requestDto.getEmail());
        return new UserResponseDto(userRepository.save(user));
    }

    // 사용자 삭제
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
