package com.sparta.week01.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sparta.week01.domain.Post;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ResponseDto {

    private LocalDateTime creatAt;
    private LocalDateTime modifiedAt;
    private Long id;
    private String title;
    private String author;
    private String content;

    public ResponseDto(Post post) {
        this.id = post.getId();
        this.creatAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.title = post.getTitle();
        this.author = post.getAuthor();
        this.content = post.getContent();

    }
}


