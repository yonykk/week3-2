package com.sparta.week01.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ErrorDto {
    private String code; //에러 코드
    private String message; //에러에 대한 설명

    //에러 코드와 메시지 등, 에러가 발생했을 때 에러 내용을 담아 전달하는 Dto
    //code, message를 입력받아 Dto를 생성하는 생성자
    public ErrorDto(String code, String message){
        this.code = code;
        this.message = message;
    }
}
