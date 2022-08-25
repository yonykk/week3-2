package com.sparta.week01.dto;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class UserRequestDto {
    private String username;
    private String password;
    private String passwordConfirm;
}