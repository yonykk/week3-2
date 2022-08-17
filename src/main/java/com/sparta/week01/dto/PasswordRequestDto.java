package com.sparta.week01.dto;

import lombok.Getter;

//Post의 비밀번호만을 담아 전달하는 Dto
@Getter
public class PasswordRequestDto {
    private String password;
}
