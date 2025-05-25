package com.example.schedulerjpa.service;

import com.example.schedulerjpa.dto.LoginRequestDto;
import com.example.schedulerjpa.dto.UserRequestDto;
import com.example.schedulerjpa.dto.UserResponseDto;
import com.example.schedulerjpa.entity.User;
import com.example.schedulerjpa.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDto createUser(UserRequestDto requestDto) {
        User user = new User(requestDto.getUsername(), requestDto.getEmail(), requestDto.getPassword());
        return new UserResponseDto(userRepository.save(user));
    }

    public void login(LoginRequestDto requestDto, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Optional<User> optionalUser = userRepository.findByEmail(requestDto.getEmail());

        if (optionalUser.isEmpty() || !optionalUser.get().getPassword().equals(requestDto.getPassword())) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "이메일 또는 비밀번호가 올바르지 않습니다.");
            return;
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("user", optionalUser.get());

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/plain;charset=UTF-8");
        response.getWriter().write("로그인에 성공하였습니다.");
    }

    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserResponseDto::new).toList();
    }

    public UserResponseDto getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));
        return new UserResponseDto(user);
    }

    public UserResponseDto updateUser(Long id, UserRequestDto requestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));
        user.update(requestDto.getUsername(), requestDto.getEmail());
        return new UserResponseDto(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
