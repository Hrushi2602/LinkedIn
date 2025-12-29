package com.hrushi.linkedin.posts_service.repository;

import com.hrushi.linkedin.posts_service.entity.PostComment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostCommentRepository extends JpaRepository<PostComment, UUID> {

    boolean existsByPostIDAndUserIdAndContent(Long postId, Long userId, String content);
}

