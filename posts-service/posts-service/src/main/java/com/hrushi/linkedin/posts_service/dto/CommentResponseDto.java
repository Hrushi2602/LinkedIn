package com.hrushi.linkedin.posts_service.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class CommentResponseDto {
    private UUID commentId;
    private Long postId;
    private Long userId;
    private String content;
    private Instant createdAt;
}
