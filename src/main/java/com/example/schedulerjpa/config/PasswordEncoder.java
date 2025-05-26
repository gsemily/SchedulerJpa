package com.example.schedulerjpa.config;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

// 비밀번호 암호화 적용
@Component
public class PasswordEncoder {
    public String encode(String rawPassword) {
        return BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, rawPassword.toCharArray()); // 비밀번호 Bcrypt 해시 처리
    }

    public boolean matches(String rawPassword, String encodedPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(rawPassword.toCharArray(), encodedPassword); // 해시 비교
        return result.verified;
    }
}
