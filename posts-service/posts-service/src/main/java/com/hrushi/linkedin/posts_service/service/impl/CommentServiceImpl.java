package com.hrushi.linkedin.posts_service.service.impl;

import com.hrushi.linkedin.posts_service.auth.UserContextHolder;
import com.hrushi.linkedin.posts_service.dto.CommentRequestDto;
import com.hrushi.linkedin.posts_service.dto.CommentResponseDto;
import com.hrushi.linkedin.posts_service.entity.PostComment;
import com.hrushi.linkedin.posts_service.repository.PostCommentRepository;
import com.hrushi.linkedin.posts_service.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final PostCommentRepository commentRepository;

    @Override
    public CommentResponseDto createComment(Long postId, CommentRequestDto commentRequestDto) {

//        if(commentRepository.existsById(postId)){
//            throw new RuntimeException("Post not found");
//        }

         Long userId = UserContextHolder.getCurrentUserId();

         if(commentRepository.existsByPostIDAndUserIdAndContent(
                 postId, userId, commentRequestDto.getContent())){
             throw  new IllegalArgumentException("Duplicate comment");
         };

        PostComment comment = PostComment.builder()
                .postID(postId)
                .userId(userId)
                .content(commentRequestDto.getContent())
                .parentCommentId(commentRequestDto.getParentCommentId())
                .build();

      PostComment postComment = commentRepository.save(comment);

        return CommentResponseDto.builder()
                .commentId(postComment.getCommnetId())
                .postId(postComment.getPostID())
                .userId(postComment.getUserId())
                .content(postComment.getContent())
                .createdAt(postComment.getCreatedAt())
                .build();
    }
}
