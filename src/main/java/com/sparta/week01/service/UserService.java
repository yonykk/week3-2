package com.sparta.week01.service;

import com.sparta.week01.common.CommonResponse;
import com.sparta.week01.common.exception.DuplicateUsernameException;
import com.sparta.week01.common.exception.InvalidPasswordException;
import com.sparta.week01.common.exception.InvalidUsernameException;
import com.sparta.week01.domain.User;
import com.sparta.week01.dto.TokenDto;
import com.sparta.week01.dto.UserRequestDto;
import com.sparta.week01.dto.UserResponseDto;
import com.sparta.week01.repository.UserRepository;
import com.sparta.week01.security.provider.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private  final TokenProvider tokenProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    public CommonResponse<UserResponseDto> signUp (UserRequestDto requestDto){
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        String confirm = requestDto.getPasswordConfirm();


        if(!username.matches("^[A-Za-z0-9]+$")||!password.matches("^[A-Za-z0-9]+$")||username.length()<4||password.length()<4)
            throw new InvalidUsernameException();
        if(!password.equals(confirm))
            throw  new InvalidPasswordException();

        Optional<User> found = userRepository.findByUsername(username);
        if(found.isPresent())
            throw new DuplicateUsernameException();

        User user = new User(username,passwordEncoder.encode(requestDto.getPassword()));
        userRepository.save(user);
        return new CommonResponse<>(new UserResponseDto(user));
    }

    public CommonResponse<HttpStatus> login(UserRequestDto userRequestDto, HttpServletResponse response){
        User user = userRepository.findByUsername(userRequestDto.getUsername()).orElseThrow(InvalidUsernameException::new);
        if(!passwordEncoder.matches(userRequestDto.getPassword(),user.getPassword()))
            throw new InvalidPasswordException();
        Authentication authentication = new UsernamePasswordAuthenticationToken(userRequestDto.getUsername(),null,null);
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(authentication);

        TokenDto tokenDto = tokenProvider.generateTokenDto(userRequestDto.getUsername());

        response.addHeader("Access-Token","BEARER "+tokenDto.getAccessToken());
        response.addHeader("Refresh-Token","BEARER "+tokenDto.getRefreshToken());

        return new CommonResponse(HttpStatus.OK);
    }


}
