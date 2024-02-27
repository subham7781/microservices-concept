package com.microservice.comment.comment.controller;

import com.microservice.comment.comment.entity.Comment;
import com.microservice.comment.comment.payload.CommentDto;
import com.microservice.comment.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    //http://localhost:8082/api/comments
    @PostMapping
    public ResponseEntity<?> SaveComment(@RequestBody CommentDto commentDto){
        CommentDto dto = commentService.saveComment(commentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public List<Comment> getAllCommentsByPostId(@PathVariable String postId){
      List<Comment> comments = commentService.getAllCommentsByPostId(postId);
      return comments;
    }
}
