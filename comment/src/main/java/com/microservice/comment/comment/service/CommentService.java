package com.microservice.comment.comment.service;


import com.microservice.comment.comment.config.RestTemplateConfig;
import com.microservice.comment.comment.entity.Comment;
import com.microservice.comment.comment.payload.CommentDto;
import com.microservice.comment.comment.payload.PostDto;
import com.microservice.comment.comment.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentService {

    @Autowired
    private RestTemplateConfig restTemplateConfig;

    @Autowired
    private CommentRepository commentRepository;

    //http://localhost:8081/api/posts/{postId}
    public CommentDto saveComment(CommentDto commentDto){
        PostDto postdto = restTemplateConfig.getRestTemplate().getForObject("http://localhost:8081/api/posts/" + commentDto.getPostId(), PostDto.class);

        if (postdto!=null){
            Comment comment = new Comment();
            comment.setPostId(commentDto.getPostId());
            comment.setName(commentDto.getName());
            comment.setEmail(commentDto.getEmail());
            comment.setBody(commentDto.getBody());

            String commentId = UUID.randomUUID().toString();
            comment.setCommentId(commentId);
            Comment save = commentRepository.save(comment);
            CommentDto dto = new CommentDto();

            dto.setCommentId(save.getCommentId());
            dto.setPostId(save.getPostId());
            dto.setName(save.getName());
            dto.setEmail(save.getEmail());
            dto.setBody(save.getBody());
            return dto;
        }else {
            return null;
        }
    }

    public List<Comment> getAllCommentsByPostId(String postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments;

    }
}
