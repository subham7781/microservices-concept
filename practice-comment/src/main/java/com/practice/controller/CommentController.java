package com.practice.controller;

import com.practice.entity.Comment;
import com.practice.payload.CommentDto;
import com.practice.service.CommentService;
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

    public ResponseEntity<?> saveComment(@RequestBody CommentDto commentDto){
        CommentDto dto = commentService.saveComment(commentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @GetMapping("/{postId}")
    public List<Comment> getAllCommentByPostId(@PathVariable String postId){
        List<Comment> comment = commentService.getAllCommentByPostId(postId);
        return comment;
    }
}
