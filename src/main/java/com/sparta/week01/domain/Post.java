package com.sparta.week01.domain;

import com.sparta.week01.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title; //제목

    @Column(nullable = false)
    private String author; //작성자


    @Column(nullable = false)
    private String content; //내용

    @OneToMany(mappedBy = "post")
    private List<Comment> commentList = new ArrayList<>();

    //Dto를 통한 생성자
    public Post(PostRequestDto requestDto, String username) {
        this.title = requestDto.getTitle();
        this.author = username;
        this.content = requestDto.getContent();
    }

    //수정시 입력받은 Dto의 내용으로 변경함.
    public void update(PostRequestDto post) {
        this.title = post.getTitle();
        this.content = post.getContent();
    }
    public void addComment(Comment comment){
        this.commentList.add(comment);
    }
    public void deleteComment(Comment comment){
        this.commentList.remove(comment);
    }
}

