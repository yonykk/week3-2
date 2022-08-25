package com.sparta.week01.service;

import com.sparta.week01.common.exception.InvalidIdException;
import com.sparta.week01.common.exception.WrongRequestException;
import com.sparta.week01.domain.Post;
import com.sparta.week01.common.CommonResponse;
import com.sparta.week01.dto.PostResponseDto;
import com.sparta.week01.dto.ResponseDto;
import com.sparta.week01.repository.PostRepository;
import com.sparta.week01.dto.PostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public CommonResponse<List<PostResponseDto>> getPost(){
        List<Post> postList = postRepository.findAllByOrderByModifiedAtDesc();
        List<PostResponseDto> result = new ArrayList<>();

        for (Post post : postList)
            result.add(new PostResponseDto(post));

        return new CommonResponse<>(result);
    }

    //수정 메서드. 입력받은 id값을 가진 Post 객체를 찾고 없으면 에러메시지를 담아서 반환, 찾으면 Dto의 내용으로 업데이트 후 true 반환
    @Transactional // SQL 쿼리가 일어나야 함
    public CommonResponse<ResponseDto>update(Long id, PostRequestDto requestDto,String username) {
        Post post = postRepository.findById(id).orElseThrow(InvalidIdException::new);
        if(!post.getAuthor().equals(username))
            throw new WrongRequestException();
        post.update(requestDto);
        return new CommonResponse<>(new ResponseDto(post));
    }

    //삭제 메서드. 입력받은 id값의 객체가 있는지 확인하고 없으면 에러메시지를 담아 반환, 있다면 삭제 후 데이터에 true 반환
    @Transactional
    public CommonResponse<Boolean> delete(Long id,String username) {
        Post post = postRepository.findById(id).orElseThrow(InvalidIdException::new);
        if(!post.getAuthor().equals(username))
            throw new WrongRequestException();
        postRepository.deleteById(id);
        return new CommonResponse<>(true);
    }

    //입력받은 id값을 가진 Post를 찾아 CommonResponse에 담아 반환한다. 없다면 에러메시지가 담긴 CommonResponse 반환
    public CommonResponse<ResponseDto> getPost(Long id) {

        Post post = postRepository.findById(id).orElseThrow(InvalidIdException::new);
        return new CommonResponse<>(new ResponseDto(post));
    }
}