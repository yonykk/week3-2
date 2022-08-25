package com.sparta.week01.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommentRequestDto {

    private Long id;
    private String content;
    private Long postId;
}
