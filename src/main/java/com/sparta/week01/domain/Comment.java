package com.sparta.week01.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.week01.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String content; //내용

    @Column(name = "post_id", insertable = false, updatable = false)
    private Long postId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "post_Id")
    private Post  post;

    @Column(nullable = false)
    private String author; //작성자


    //Dto를 통한 생성자
    public Comment(CommentRequestDto requestDto,Post post, String author) {
        this.author = author;
        this.content = requestDto.getContent();
        this.post = post;
    }

    //수정시 입력받은 Dto의 내용으로 변경함.
    public void update(CommentRequestDto requestDto, Post post) {
        this.content = requestDto.getContent();
        this.post = post;
    }
}

