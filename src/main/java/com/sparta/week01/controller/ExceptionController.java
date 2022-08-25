package com.sparta.week01.controller;

import com.sparta.week01.common.CommonResponse;
import com.sparta.week01.common.exception.*;
import com.sparta.week01.common.exception.Exception;
import com.sparta.week01.domain.Post;
import com.sparta.week01.dto.ErrorDto;
import com.sparta.week01.service.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ExceptionController {

    private final ResponseService responseService;


    //전역 예외처리 메소드. Exception의 메시지와 코드를 받아와서 responseService로 넘겨준다.
    @ExceptionHandler(InvalidIdException.class)
    private CommonResponse<Post> invalidateIdException(InvalidIdException e){
        log.info(e.getMessage());
        ErrorDto dto = new ErrorDto(Exception.INVALIDATE_ID.getCode(),Exception.INVALIDATE_ID.getMessage());
        return responseService.getErrorResponse(dto);
    }

    @ExceptionHandler(InvalidUsernameException.class)
    private CommonResponse<Post> invalidateUsernameException(InvalidUsernameException e){
        log.info(e.getMessage());
        ErrorDto dto = new ErrorDto(Exception.INVALIDATE_USERNAME.getCode(),Exception.INVALIDATE_USERNAME.getMessage());
        return responseService.getErrorResponse(dto);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    private CommonResponse<Post> invalidatePasswordException(InvalidPasswordException e){
        log.info(e.getMessage());
        ErrorDto dto = new ErrorDto(Exception.INVALIDATE_PASSWORD.getCode(),Exception.INVALIDATE_PASSWORD.getMessage());
        return responseService.getErrorResponse(dto);
    }

    @ExceptionHandler(DuplicateUsernameException.class)
    private CommonResponse<Post> invalidateIdException(DuplicateUsernameException e){
        log.info(e.getMessage());
        ErrorDto dto = new ErrorDto(Exception.DUPLICATE_USERNAME.getCode(),Exception.DUPLICATE_USERNAME.getMessage());
        return responseService.getErrorResponse(dto);
    }

    @ExceptionHandler(WrongRequestException.class)
    private CommonResponse<Post> wrongRequestException(WrongRequestException e){
        log.info(e.getMessage());
        ErrorDto dto = new ErrorDto(Exception.WRONG_REQUEST.getCode(),Exception.WRONG_REQUEST.getMessage());
        return responseService.getErrorResponse(dto);
    }

    @ExceptionHandler(InvalidTokenException.class)
    private CommonResponse<Post> invalidTokenException(InvalidTokenException e){
        log.info(e.getMessage());
        ErrorDto dto = new ErrorDto(Exception.INVALIDATE_TOKEN.getCode(),Exception.INVALIDATE_TOKEN.getMessage());
        return responseService.getErrorResponse(dto);
    }
}
