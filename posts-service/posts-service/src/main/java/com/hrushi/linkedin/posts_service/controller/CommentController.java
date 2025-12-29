package com.hrushi.linkedin.posts_service.controller;

import com.hrushi.linkedin.posts_service.dto.CommentRequestDto;
import com.hrushi.linkedin.posts_service.dto.CommentResponseDto;
import com.hrushi.linkedin.posts_service.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postId}")
    public ResponseEntity<?> createComment(@PathVariable Long postId,
                                           @RequestBody @Validated CommentRequestDto commentRequestDto
    ){

        CommentResponseDto response = commentService.createComment(postId, commentRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

}
