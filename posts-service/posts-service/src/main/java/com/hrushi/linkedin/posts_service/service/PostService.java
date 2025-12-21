package com.hrushi.linkedin.posts_service.service;

import com.hrushi.linkedin.posts_service.dto.PostCreateDto;
import com.hrushi.linkedin.posts_service.dto.PostDto;
import com.hrushi.linkedin.posts_service.entity.Post;
import com.hrushi.linkedin.posts_service.exception.ResourceNotFoundException;
import com.hrushi.linkedin.posts_service.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.SecondaryRow;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

  //  private final KafkaTemplate<> kafkaTemplate;

    public PostDto createPost(PostCreateDto postDto, Long userId) {
        Post post = modelMapper.map(postDto, Post.class);
        post.setUserId(userId);

        Post savedPost = postRepository.save(post);

        return modelMapper.map(savedPost,PostDto.class);
    }

    public PostDto getPostById(Long postId) {
        log.debug("postId is" , postId);

        Post post = postRepository.findById(postId).orElseThrow(()->
           new ResourceNotFoundException("post id is not found" +postId));
        return modelMapper.map(post,PostDto.class);
    }

    public List<PostDto> getAllPostOfUser(Long userId) {

      List<Post> posts=   postRepository.findByUserId(userId);

      return  posts.stream()
                .map((element) -> modelMapper.map(element, PostDto.class ))
                .collect(Collectors.toList());
    }
}
