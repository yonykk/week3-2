package com.sparta.week01.dto;

import com.sparta.week01.domain.Comment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class CommentResponseDto {

    private LocalDateTime creatAt;
    private LocalDateTime modifiedAt;
    private Long id;
    private String content;
    private String author;


    public CommentResponseDto(Comment comment){
        this.id = comment.getId();
        this.creatAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
        this.content = comment.getContent();
        this.author = comment.getAuthor();


    }
}
