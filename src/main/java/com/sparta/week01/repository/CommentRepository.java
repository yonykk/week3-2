package com.sparta.week01.repository;

import com.sparta.week01.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostIdOrderByModifiedAtDesc(Long postId); //수정 날짜를 기준으로 내림차순 정렬하여 반환
    void deleteAllBypostId(Long postId);
    }
