package com.sparta.week01.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sparta.week01.dto.PasswordRequestDto;
import com.sparta.week01.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
@JsonIgnoreProperties({"password"}) //비밀번호는 가리는 것으로 처리한다.
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

    @Column(nullable = false)
    private String password; //비밀번호


    //Dto를 통한 생성자
    public Post(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.author = requestDto.getAuthor();
        this.content = requestDto.getContent();
        this.password = requestDto.getPassword();
    }

    //수정시 입력받은 Dto의 내용으로 변경함.
    public void update(PostRequestDto post) {
        this.title = post.getTitle();
        this.author = post.getAuthor();
        this.content = post.getContent();
        this.password = post.getPassword();
    }
}

