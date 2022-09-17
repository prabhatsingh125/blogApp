package com.springboot.blog.service;

import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Override
    public PostDto createPost(PostDto postDto) {

        // convert DTO to entity
        Post newPost = new Post();
        newPost.setTitle(postDto.getTitle());
        newPost.setDescription(postDto.getDescription());
        newPost.setContent(postDto.getContent());
        Post postEntity = postRepository.save(newPost);

        // convert entity to DTO
        PostDto postResponse = new PostDto();
        postResponse.setId(postEntity.getId());
        postResponse.setTitle(postEntity.getTitle());
        postResponse.setDescription(postEntity.getDescription());
        postResponse.setContent(postEntity.getContent());

        return postResponse;

    }
}
