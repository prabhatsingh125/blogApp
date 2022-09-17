package com.springboot.blog.controller;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    PostService postService;

    // create Blog Post
    @PostMapping
    ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        ResponseEntity<PostDto> responseEntity = new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
        return responseEntity;
    }

    // get All Posts
    @GetMapping
    List<PostDto> getAllPosts(){
        return postService.getAllPosts();
    }

    // get Post By Id
    @GetMapping("/{id}")
    ResponseEntity<PostDto> getPostById(@PathVariable("id") long id){
        ResponseEntity<PostDto> responseEntity = new ResponseEntity<>(postService.getPostById(id),HttpStatus.OK);
        return responseEntity;
    }

    // Update Post By Id
    @PutMapping("/{id}")
    ResponseEntity<PostDto> updatePostById(@PathVariable("id") long id ,@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.updatePostById(id,postDto) , HttpStatus.OK);

    }

    // Delete Post By Id
    @DeleteMapping("/{id}")
    ResponseEntity<String> deletePostById(@PathVariable("id") long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post Entity " + id +  " Deleted Successfully",HttpStatus.OK);
    }
}
