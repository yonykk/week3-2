package com.sparta.week01.dto;

import com.sparta.week01.domain.Post;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class PostResponseDto {

    private LocalDateTime creatAt;
    private LocalDateTime modifiedAt;
    private Long id;
    private String title;
    private String author;


    public PostResponseDto(Post post){
        this.id = post.getId();
        this.creatAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.title = post.getTitle();
        this.author = post.getAuthor();


    }
}
