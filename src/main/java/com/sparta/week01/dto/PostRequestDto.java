package com.sparta.week01.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

//Post의 데이터를 전달하는 PostRequestDto

@Getter
@Setter
@RequiredArgsConstructor
public class PostRequestDto {
    private String title;
    private String author;
    private String content;
    private String password;
    private String creatAt;
    private String modifiedAt;

}
