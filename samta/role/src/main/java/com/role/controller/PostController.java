package com.role.controller;

import com.role.payload.PostDto;
import com.role.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    //http://localhost:8080/api/posts
    @PostMapping
    public ResponseEntity<PostDto>createPost( @RequestBody  PostDto postDto){
        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    //http://localhost:8080/api/posts
    @GetMapping
    public List<PostDto> getAllPost(){
        return postService.getAllPost();
    }
    //http://localhost:8080/api/posts/1
    //HERE 1 IS id
    @GetMapping("/{id}")
    public ResponseEntity<PostDto>getPostById(@PathVariable("id")long id ){
        PostDto dto = postService.getPostById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    //http://localhost:8080/api/posts/1
    //HERE 1 IS id
    @PutMapping("/{id}")
    public ResponseEntity<PostDto>updatePost(@RequestBody PostDto postDto,@PathVariable("id") long id){
        PostDto updateDto = postService.updatePost(postDto, id);
        return new ResponseEntity<>(updateDto,HttpStatus.OK);
    }
    //http://localhost:8080/api/posts/1
    //HERE 1 IS id
    @DeleteMapping("/{id}")
    public ResponseEntity<String>deletePostById(@PathVariable("id") long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post entity deleted! sucessfully",HttpStatus.OK);
    }
}
