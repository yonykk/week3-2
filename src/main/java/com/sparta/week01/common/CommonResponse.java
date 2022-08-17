package com.sparta.week01.common;

import com.sparta.week01.dto.ErrorDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


//출력 Response의 형태를 통일하기 위한 CommonResponse 클래스
@RequiredArgsConstructor
@Getter
@Setter
public class CommonResponse<T> {
    private boolean success; //동작 성공 여부
    private T data; //담고있는 데이터
    private ErrorDto error; //동작 실패시 에러 내용

    //정상적으로 데이터가 들어온 경우 생성자
    public CommonResponse(T RequestDto){
        success = true;
        data = RequestDto;
    }

    //동작이 실패하여 에러메시지를 담은 Dto가 들어온 경우
    public CommonResponse(ErrorDto errorDto) {
        success = false;
        this.error.setCode(errorDto.getCode());
        this.error.setMessage(errorDto.getMessage());
    }
}
