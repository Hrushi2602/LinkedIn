package com.hrushi.linkedin.posts_service.controller;

import com.hrushi.linkedin.posts_service.auth.UserContextHolder;
import com.hrushi.linkedin.posts_service.dto.PostCreateDto;
import com.hrushi.linkedin.posts_service.dto.PostDto;
import com.hrushi.linkedin.posts_service.entity.Post;
import com.hrushi.linkedin.posts_service.service.PostService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostCreateDto postDto, HttpServletRequest httpServletRequest) {

        //String userId = httpServletRequest.getHeader("X-user-id");
        PostDto createdPost = postService.createPost(postDto, 1L);

        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable Long postId) {
        Long userId = UserContextHolder.getCurrentUserId();

        //String userId = httpServletRequest.getHeader("x-User-Id");
        PostDto postDto = postService.getPostById(postId);

        return ResponseEntity.ok(postDto);
    }

    @GetMapping("users/{userId}/allPosts")
    public ResponseEntity<List<PostDto>> getAllPostsUser(@PathVariable Long userId) {
        List<PostDto> posts = postService.getAllPostOfUser(userId);

        return ResponseEntity.ok(posts);
    }

}

