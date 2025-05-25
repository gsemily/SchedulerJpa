package com.example.schedulerjpa.service;

import com.example.schedulerjpa.dto.UserRequestDto;
import com.example.schedulerjpa.dto.UserResponseDto;
import com.example.schedulerjpa.entity.User;
import com.example.schedulerjpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDto createUser(UserRequestDto requestDto) {
        User user = new User(requestDto.getUsername(), requestDto.getEmail());
        return new UserResponseDto(userRepository.save(user));
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
