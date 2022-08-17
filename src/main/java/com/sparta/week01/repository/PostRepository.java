package com.sparta.week01.repository;

import com.sparta.week01.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByModifiedAtDesc(); //수정 날짜를 기준으로 내림차순 정렬하여 반환
    }
