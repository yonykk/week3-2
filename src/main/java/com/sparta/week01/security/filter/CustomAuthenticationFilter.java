package com.sparta.week01.security.filter;

import com.sparta.week01.security.UserDetailsServiceImpl;
import com.sparta.week01.security.provider.TokenProvider;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@RequiredArgsConstructor
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    private final String secretKey = "sparta123!@#456$%^123!@#456$%^678^&*sparta123!@#456$%^123!@#456$%^678^&*sparta123!@#456$%^123!@#456$%^678^&*sparta123!@#456$%^123!@#456$%^678^&*";
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "BEARER ";


    private final TokenProvider jwtTokenProvider;
    private final UserDetailsServiceImpl userDetailsService;

    // request header 에서 토큰을 가져오거나
    // Request로 들어오는 Jwt Token의 유효성을 검증하는 filter를 filterChain에 등록합니다.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = resolveToken(request);

        if (token != null && jwtTokenProvider.validateToken(token)) {   // token 검증
            String username = jwtTokenProvider.getUsername(token);
                Authentication authentication = new UsernamePasswordAuthenticationToken(jwtTokenProvider.getUsername(token), null, null);
                SecurityContext context = SecurityContextHolder.getContext();
                context.setAuthentication(authentication);

        }

        filterChain.doFilter(request, response);
    }

    // Request Header 에서 토큰 정보를 꺼내오기
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }
}




