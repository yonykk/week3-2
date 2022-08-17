package com.sparta.week01.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Exception {
    INVALIDATE_ID("NULL_POST_ID","post id isn't exist");

    private final String code;
    private final String message;
}
