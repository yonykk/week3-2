package com.sparta.week01.dto;

import com.sparta.week01.domain.Comment;
import com.sparta.week01.domain.Post;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class ResponseDto {

    private LocalDateTime creatAt;
    private LocalDateTime modifiedAt;
    private Long id;
    private String title;
    private String author;
    private String content;
    private List<Comment> commentList;

    public ResponseDto(Post post) {
        this.id = post.getId();
        this.creatAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.title = post.getTitle();
        this.author = post.getAuthor();
        this.content = post.getContent();
        this.commentList = new ArrayList<>(post.getCommentList());
    }
}


