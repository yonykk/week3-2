package com.sparta.week01.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Exception {
    INVALIDATE_ID("NULL_POST_ID","post id isn't exist"), //ID에 해당하는 글이 없을 때 표시할 에러
    INVALIDATE_USERNAME("NULL_USERNAME","사용자가 존재하지 않습니다."),
    INVALIDATE_PASSWORD("NULL_PASSWORD","비밀번호와 비밀번호 확인이 일치하지 않습니다."),
    WRONG_REQUEST("WRONG_REQUEST","작성자만 삭제할 수 있습니다."),
    DUPLICATE_USERNAME("DUPLICATE_ID","중복된 닉네임입니다."),
    INVALIDATE_TOKEN("NULL_TOKEN"," Token이 유효하지 않습니다.");
    private final String code;
    private final String message;
}
