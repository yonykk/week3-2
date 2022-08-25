package com.sparta.week01.dto;

import com.sparta.week01.domain.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private final Long id;
    private final String username;

    public UserResponseDto(User user){
        this.id = user.getId();
        this.username = user.getUsername();
    }

}
