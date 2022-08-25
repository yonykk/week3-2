package com.sparta.week01.security.provider;

import com.sparta.week01.domain.RefreshToken;
import com.sparta.week01.dto.TokenDto;
import com.sparta.week01.repository.RefreshTokenRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@RequiredArgsConstructor
@Configuration
public class TokenProvider {
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;


    private static final String BEARER_TYPE = "bearer";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30;            // 30분
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;  // 7일

    private final Key key;
    private final String secretKey = "sparta123!@#456$%^123!@#456$%^678^&*sparta123!@#456$%^123!@#456$%^678^&*sparta123!@#456$%^123!@#456$%^678^&*sparta123!@#456$%^123!@#456$%^678^&*";
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;


    public TokenProvider() {
        byte[] keyBytes = DatatypeConverter.parseBase64Binary(secretKey);
        this.key = new SecretKeySpec(keyBytes, SignatureAlgorithm.HS512.getJcaName());
    }

    // 토큰으로 회원 정보 조회
    public String getUsername (String token){
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }



    public TokenDto generateTokenDto(String username) {
        long now = (new Date()).getTime();

        // Access Token 생성
        Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        String accessToken = Jwts.builder()
                .setSubject(username)       // payload "sub": "name"
                .setExpiration(accessTokenExpiresIn)        // payload "exp": 1516239022 (예시)
                .signWith(key,signatureAlgorithm)    // header "alg": "HS512"
                .compact();

        // Refresh Token 생성
        String refreshToken = Jwts.builder()
                .setExpiration(new Date(now + REFRESH_TOKEN_EXPIRE_TIME))
                .signWith(key,signatureAlgorithm)
                .compact();

        RefreshToken refresh = new RefreshToken(refreshToken,username);
        refreshTokenRepository.save(refresh);

        return TokenDto.builder()
                .refreshToken(refreshToken)
                .accessToken(accessToken)
                .grantType(BEARER_TYPE)
                .accessTokenExpiresIn(accessTokenExpiresIn.getTime())
                .build();
    }

        // 토큰의 유효 및 만료 확인
        public boolean validateToken (String token){
            try {
                Long now = new Date().getTime();
                return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getExpiration().getTime() >= now;
            } catch(Exception e) {
                return false;
            }
    }
}