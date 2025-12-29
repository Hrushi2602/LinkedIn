package com.hrushi.linkedin.posts_service.service;

import com.hrushi.linkedin.posts_service.dto.CommentRequestDto;
import com.hrushi.linkedin.posts_service.dto.CommentResponseDto;

import java.util.UUID;

public interface CommentService {
    CommentResponseDto createComment(Long postId, CommentRequestDto commentRequestDto);
}
