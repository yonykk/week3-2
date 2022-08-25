package com.sparta.week01.controller;

import com.sparta.week01.common.CommonResponse;
import com.sparta.week01.dto.UserRequestDto;
import com.sparta.week01.dto.UserResponseDto;
import com.sparta.week01.repository.UserRepository;
import com.sparta.week01.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
public class UserController {


    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/api/user/login")
    public CommonResponse<HttpStatus> login(@RequestBody UserRequestDto userRequestDto, HttpServletResponse response){
        return userService.login(userRequestDto,response);
}

    // 회원 가입 요청 처리
    @PostMapping("/api/user/signup")
    public CommonResponse<UserResponseDto> signup(@RequestBody UserRequestDto requestDto) {
        return userService.signUp(requestDto);
    }
}