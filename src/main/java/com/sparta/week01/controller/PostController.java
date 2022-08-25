package com.sparta.week01.controller;

import com.sparta.week01.domain.Post;
import com.sparta.week01.common.CommonResponse;
import com.sparta.week01.dto.PostResponseDto;
import com.sparta.week01.dto.ResponseDto;
import com.sparta.week01.repository.PostRepository;
import com.sparta.week01.dto.PostRequestDto;
import com.sparta.week01.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostRepository postRepository;

    private final PostService postService;

    //저장된 모든 Post의 목록을 조회하는 메서드. CommonResponse 객체로 감싸 형식에 맞게 반환한다.
    @GetMapping("/api/post")
    public CommonResponse<List<PostResponseDto>> getPost() {
        return postService.getPost();
    }

    // Post 작성 메서드. requestDto를 통해 전달된 정보를 받아와 Post를 생성하고 DB에 저장한다.
    @PostMapping("/api/auth/post")
    public Post createPostList(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal String username) {
        Post post = new Post(requestDto, username);
        return postRepository.save(post);
    }

    //Post 수정 메서드. DB에서 같은 값의 id를 찾아내어 전달받은 dto의 값으로 바꿔치기한다.
    @PutMapping("/api/auth/post/{id}")
    public CommonResponse<ResponseDto> updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto, @AuthenticationPrincipal String username) {
        return postService.update(id, requestDto, username);
    }

    //Post 삭제 메서드. DB로 id값을 전달하여 해당 Post를 DB에서 삭제한다. 성공 여부를 반환한다.
    @DeleteMapping("/api/auth/post/{id}")
    public CommonResponse<Boolean> deletePost(@PathVariable Long id, @AuthenticationPrincipal String username) {
        return postService.delete(id, username);
    }

    //해당 id의 Post를 조회한다. CommonResponse 객체로 형식에 맞게 감싸서 반환한다.
    @GetMapping("/api/post/{id}")
    public CommonResponse<ResponseDto> getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }


}

