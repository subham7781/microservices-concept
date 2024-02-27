package com.practice.controller;

import com.practice.payload.PostDto;
import com.practice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<?> savePost(@RequestBody PostDto postDto){
        PostDto dto = postService.savePost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @GetMapping("/{postId}")
    public ResponseEntity<?> GetByPostId(@PathVariable String postId){
        PostDto dto = postService.GetByPostId(postId);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    //http://localhost:8081/api/posts/{postId}/comments
    @GetMapping("/{postId}/comments")
    public ResponseEntity<?> getPostWithComment(@PathVariable String postId){
        PostDto postWithComment = postService.getPostWithComment(postId);
        return new ResponseEntity<>(postWithComment,HttpStatus.OK);

    }
}
