package com.example.schedulerjpa.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

// Filter 구현체 LoginFilter
@Slf4j
public class LoginFilter implements Filter {
    private static final String[] WHITE_LIST = {"/", "/users/signup", "/users/login"};

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        // 다양한 기능을 사용하기 위해 다운 캐스팅
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestURI = httpRequest.getRequestURI();

        // 로그인을 체크 해야하는 URL인지 검사
        // whiteListURL에 포함된 경우 true 반환
        if (!isWhiteList(requestURI)) {

            // 세션이 존재할 경우 가져옴
            HttpSession session = httpRequest.getSession(false);

            // 로그인하지 않은 사용자의 경우
            if (session == null || session.getAttribute("user") == null) {
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "로그인 해주세요.");
                return;
            }
        }
        chain.doFilter(request, response);
    }

    // request URI가 whiteListURL에 포함되는지 확인
    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
