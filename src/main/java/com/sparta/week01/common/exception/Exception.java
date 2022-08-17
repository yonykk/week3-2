package com.sparta.week01.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Exception {
    INVALIDATE_ID("NULL_POST_ID","post id isn't exist"); //ID에 해당하는 글이 없을 때 표시할 에러

    private final String code;
    private final String message;
}
