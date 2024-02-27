package com.practice.service;

import com.practice.config.RestTemplateCofig;
import com.practice.entity.Post;
import com.practice.payload.PostDto;
import com.practice.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private RestTemplateCofig restTemplateCofig;

    public PostDto savePost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        String postId = UUID.randomUUID().toString();
        post.setPostId(postId);
        Post save = postRepository.save(post);

        PostDto dto = new PostDto();
        dto.setPostId(save.getPostId());
        dto.setTitle(save.getTitle());
        dto.setDescription(save.getDescription());
        dto.setContent(save.getContent());
        return dto;

    }

    public PostDto GetByPostId(String postId) {
        Post post = postRepository.findById(postId).get();
        PostDto dto = new PostDto();
        dto.setPostId(post.getPostId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());
        return dto;

    }

    public PostDto getPostWithComment(String postId) {
        Post post = postRepository.findById(postId).get();
        ArrayList comments = restTemplateCofig.getRestTemplate().getForObject("http://localhost:8082/api/comments/" + postId, ArrayList.class);
        PostDto dto = new PostDto();
        dto.setPostId(post.getPostId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());
        dto.setComments(comments);
        return dto;

    }
}
