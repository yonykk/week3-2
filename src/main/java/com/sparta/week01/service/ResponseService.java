package com.sparta.week01.service;

import com.sparta.week01.common.CommonResponse;
import com.sparta.week01.dto.ErrorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResponseService {

    //에러메시지를 담은 dto를 CommonResponse를 이용하여 통일된 형식으로 감싼 후 반환한다.
    public CommonResponse getErrorResponse(ErrorDto errorDto){
        CommonResponse response = new CommonResponse();
        response.setError(errorDto);
        return response;
    }
}
