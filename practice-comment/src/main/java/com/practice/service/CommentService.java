package com.practice.service;


import com.practice.config.RestTemplateConfig;
import com.practice.entity.Comment;
import com.practice.payload.CommentDto;
import com.practice.payload.PostDto;
import com.practice.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private RestTemplateConfig restTemplateConfig;

    public CommentDto saveComment(CommentDto commentDto) {
        PostDto postId = restTemplateConfig.getRestTemplate().getForObject("http://localhost:8081/api/posts/" + commentDto.getPostId(), PostDto.class);
        if (postId!=null){
            Comment comment = new Comment();
            comment.setName(commentDto.getName());
            comment.setEmail(commentDto.getEmail());
            comment.setBody(commentDto.getBody());
            comment.setPostId(commentDto.getPostId());

            String commentId = UUID.randomUUID().toString();
            comment.setCommentId(commentId);

            Comment save = commentRepository.save(comment);
            CommentDto dto = new CommentDto();
            dto.setName(save.getName());
            dto.setEmail(save.getEmail());
            dto.setBody(save.getBody());
            dto.setPostId(save.getPostId());
            dto.setCommentId(save.getCommentId());
            return dto;
        }else {
            return null;
        }

    }

    public List<Comment> getAllCommentByPostId(String postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments;
    }
}
