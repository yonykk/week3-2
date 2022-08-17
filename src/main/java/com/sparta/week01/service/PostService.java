package com.sparta.week01.service;

import com.sparta.week01.common.exception.InvalidIdException;
import com.sparta.week01.domain.Post;
import com.sparta.week01.common.CommonResponse;
import com.sparta.week01.dto.PasswordRequestDto;
import com.sparta.week01.repository.PostRepository;
import com.sparta.week01.dto.PostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    //수정 메서드. 입력받은 id값을 가진 Post 객체를 찾고 없으면 에러메시지를 담아서 반환, 찾으면 Dto의 내용으로 업데이트 후 true 반환
    @Transactional // SQL 쿼리가 일어나야 함
    public CommonResponse<Boolean>update(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(InvalidIdException::new);
        post.update(requestDto);
        return new CommonResponse<>(true);
    }

    //삭제 메서드. 입력받은 id값의 객체가 있는지 확인하고 없으면 에러메시지를 담아 반환, 있다면 삭제 후 데이터에 true 반환
    @Transactional
    public CommonResponse<Boolean> delete(Long id) {
        Post post = postRepository.findById(id).orElseThrow(InvalidIdException::new);
        postRepository.deleteById(id);
        return new CommonResponse<>(true);
    }

    //입력받은 id값을 가진 Post를 찾아 CommonResponse에 담아 반환한다. 없다면 에러메시지가 담긴 CommonResponse 반환
    public CommonResponse<Post> getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(InvalidIdException::new);
        return new CommonResponse<>(post);
    }

    //입력받은 비밀번호가 해당 id값의 Post객체의 비밀번호와 같은지를 판단하는 메서드. 해당 Id의 객체가 없으면 에러 메시지를 반환하고 있다면 같은지의 여부를 데이터에 담아 보낸다.
    public CommonResponse<Boolean> equalPassword(Long id, PasswordRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(InvalidIdException::new);
        return new CommonResponse<>(post.getPassword().equals(requestDto.getPassword()));
    }
}