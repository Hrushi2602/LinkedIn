package com.hrushi.linkedin.posts_service.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tl_postcomments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID commnetId;

    private Long postID;

    private Long userId;

    @Column(nullable = false, length = 2000)
    private String content;

    private UUID parentCommentId; // for nested comments

    @Builder.Default
    private Boolean deleted = false;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    @Version
    private Long version; // optimistic locking

}
