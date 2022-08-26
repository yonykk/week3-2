package com.sparta.week01.controller;

import com.sparta.week01.common.CommonResponse;
import com.sparta.week01.common.exception.InvalidIdException;
import com.sparta.week01.domain.Comment;
import com.sparta.week01.domain.Post;
import com.sparta.week01.dto.CommentRequestDto;
import com.sparta.week01.dto.CommentResponseDto;
import com.sparta.week01.repository.CommentRepository;
import com.sparta.week01.repository.PostRepository;
import com.sparta.week01.security.provider.TokenProvider;
import com.sparta.week01.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentRepository commentRepository;

    private final CommentService commentService;
    private final TokenProvider tokenProvider;
    private final PostRepository postRepository;

    //저장된 모든 Comment의 목록을 조회하는 메서드. CommonResponse 객체로 감싸 형식에 맞게 반환한다.
    @GetMapping("/api/comment/{id}")
    public CommonResponse<List<CommentResponseDto>> getCommentList(@PathVariable Long id) {
        return commentService.getComment(id);
    }

    // Comment 작성 메서드. requestDto를 통해 전달된 정보를 받아와 Comment를 생성하고 DB에 저장한다.
    @PostMapping("/api/auth/comment")
    public CommonResponse<CommentResponseDto> createCommentList(@RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal String username) {
        Post post = postRepository.findById(requestDto.getPostId()).orElseThrow(InvalidIdException::new);
        Comment comment = new Comment(requestDto,post, username);
        commentRepository.save(comment);
        return new CommonResponse<>(new CommentResponseDto(comment));
    }

    //Comment 수정 메서드. DB에서 같은 값의 id를 찾아내어 전달받은 dto의 값으로 바꿔치기한다. 수정된 comment 반환
    @PutMapping("/api/comment/{id}")
    public CommonResponse<CommentResponseDto> updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal String username) {
        return commentService.update(id, requestDto, username);
    }

    //Comment 삭제 메서드. DB로 id값을 전달하여 해당 Comment를 DB에서 삭제한다. 성공 여부를 반환한다.
    @DeleteMapping("/api/auth/comment/{id}")
    public CommonResponse<Boolean> deleteComment(@PathVariable Long id, @AuthenticationPrincipal String username) {
        return commentService.delete(id, username);
    }
}

