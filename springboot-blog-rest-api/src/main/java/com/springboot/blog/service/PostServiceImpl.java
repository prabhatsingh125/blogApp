package com.springboot.blog.service;

import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Override
    public PostDto createPost(PostDto postDto) {

        // convert DTO to entity
        Post newPost = convertDTOtoEntity(postDto);
        Post postEntity = postRepository.save(newPost);

        // convert entity to DTO
        PostDto postResponse = convertEntityToDto(postEntity);
        return postResponse;

    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        //convert List of Entity to Dto
        return posts.stream().map(x -> convertEntityToDto(x)).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(long id) {
        Post response =  postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id",id));
        return convertEntityToDto(response);
    }

    @Override
    public PostDto updatePostById(long id, PostDto postDto) {
        Post post = convertDTOtoEntity(postDto);
        post.setId(postDto.getId());
        Post post1 = postRepository.save(post);
        return convertEntityToDto(post1);
    }

    @Override
    public void deletePostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",id));
        postRepository.delete(post);

    }

    private Post convertDTOtoEntity(PostDto postDto){
        Post newPost = new Post();
        newPost.setTitle(postDto.getTitle());
        newPost.setDescription(postDto.getDescription());
        newPost.setContent(postDto.getContent());
        return newPost;
    }
    private PostDto convertEntityToDto(Post post){
        PostDto postResponse = new PostDto();
        postResponse.setId(post.getId());
        postResponse.setTitle(post.getTitle());
        postResponse.setDescription(post.getDescription());
        postResponse.setContent(post.getContent());
        return postResponse;
    }
}
