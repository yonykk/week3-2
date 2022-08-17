package com.sparta.week01.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sparta.week01.domain.Post;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

//Post의 데이터를 전달하는 PostRequestDto

@Getter
@Setter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostRequestDto {

    private LocalDateTime creatAt;
    private LocalDateTime modifiedAt;
    private Long id;
    private String title;
    private String author;
    private String content;
    private String password;

    public PostRequestDto(Post post){
        this.id = post.getId();
        this.creatAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.title = post.getTitle();
        this.author = post.getAuthor();

    }
}
