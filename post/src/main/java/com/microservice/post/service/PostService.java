package com.microservice.post.service;

import com.microservice.post.config.RestTemplateConfig;
import com.microservice.post.entity.Post;
import com.microservice.post.payload.PostDto;
import com.microservice.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private RestTemplateConfig restTemplateConfig;

    public PostDto savePost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        String postId = UUID.randomUUID().toString();
        post.setPostId(postId);

        Post save = postRepository.save(post);

        PostDto dto =new PostDto();
        dto.setPostId(save.getPostId());
        dto.setTitle(save.getTitle());
        dto.setDescription(save.getDescription());
        dto.setContent(save.getContent());
        return dto;
    }

    public PostDto FindPostByID(String postId) {

        Post post = postRepository.findById(postId).get();

        PostDto dto = new PostDto();
        dto.setPostId(post.getPostId());
        dto.setContent(post.getContent());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        return dto;
    }

    //eureka server
    public PostDto getPostWithComments(String postId) {
        Post post = postRepository.findById(postId).get();
        ArrayList comments = restTemplateConfig.getRestTemplate().getForObject("http://localhost:8082/api/comments/" + postId, ArrayList.class);
        PostDto postdto = new PostDto();
        postdto.setPostId(post.getPostId());
        postdto.setTitle(post.getTitle());
        postdto.setDescription(post.getDescription());
        postdto.setContent(post.getContent());
        postdto.setComments(comments);
        return postdto;
    }
}
