package com.sparta.week01.service;

import com.sparta.week01.common.CommonResponse;
import com.sparta.week01.common.exception.InvalidIdException;
import com.sparta.week01.common.exception.WrongRequestException;
import com.sparta.week01.domain.Comment;
import com.sparta.week01.domain.Post;
import com.sparta.week01.dto.CommentRequestDto;
import com.sparta.week01.dto.CommentResponseDto;
import com.sparta.week01.repository.CommentRepository;
import com.sparta.week01.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommonResponse<List<CommentResponseDto>> getComment(Long id){
        List<Comment> commentList = commentRepository.findAllByPostIdOrderByModifiedAtDesc(id);
        List<CommentResponseDto> result = new ArrayList<>();
        for (Comment comment : commentList)
            result.add(new CommentResponseDto(comment));

        return new CommonResponse<>(result);
    }

    //수정 메서드. 입력받은 id값을 가진 comment 객체를 찾고 없으면 에러메시지를 담아서 반환, 찾으면 Dto의 내용으로 업데이트 후 true 반환
    @Transactional // SQL 쿼리가 일어나야 함
    public CommonResponse<CommentResponseDto>update(Long id, CommentRequestDto requestDto, String username) {
        Comment comment = commentRepository.findById(id).orElseThrow(InvalidIdException::new);
        Post post = postRepository.findById(requestDto.getPostId()).orElseThrow(InvalidIdException::new);
        if(!comment.getAuthor().equals(username))
            throw new WrongRequestException();
        comment.update(requestDto,post);
        return new CommonResponse<>(new CommentResponseDto(comment));
    }

    //삭제 메서드. 입력받은 id값의 객체가 있는지 확인하고 없으면 에러메시지를 담아 반환, 있다면 삭제 후 데이터에 true 반환
    @Transactional
    public CommonResponse<Boolean> delete(Long id, String username) {
        Comment comment = commentRepository.findById(id).orElseThrow(InvalidIdException::new);
        if(!comment.getAuthor().equals(username))
            throw new WrongRequestException();
        commentRepository.deleteById(id);
        return new CommonResponse<>(true);
    }
}
