package com.hrushi.linkedin.posts_service.service;

import com.hrushi.linkedin.posts_service.entity.PostLike;
import com.hrushi.linkedin.posts_service.exception.ResourceNotFoundException;
import com.hrushi.linkedin.posts_service.repository.PostLikeRepository;
import com.hrushi.linkedin.posts_service.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostLikeService {

    private  final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;

    public void likePost(Long postId, Long userId){
        boolean exists = postRepository.existsById(postId);

        if(!exists) throw new ResourceNotFoundException("Post not found");

        boolean alreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId, postId);

        if(alreadyLiked) throw new ResourceNotFoundException("can not like same post");

        PostLike postLike = new PostLike();

        postLike.setPostId(postId);
        postLike.setUserId(userId);

        postLikeRepository.save(postLike);
    }


    public void unlikePost(Long postId, Long userId) {

        boolean exists = postRepository.existsById(postId);
        if(!exists) throw new ResourceNotFoundException("Post not found");

        boolean alreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId, postId);

        if(!alreadyLiked) throw new ResourceNotFoundException("can not unlike post");

        postLikeRepository.deleteByUserIdAndPostId(userId,postId);
    }
}
