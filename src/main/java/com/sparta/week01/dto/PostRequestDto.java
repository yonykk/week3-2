package com.sparta.week01.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

//Post의 데이터를 전달하는 PostRequestDto

@Getter
@RequiredArgsConstructor
public class PostRequestDto {

    private Long id;
    private String title;
    private String content;
    private String password;

}
