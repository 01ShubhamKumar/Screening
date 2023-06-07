package com.role.service.impl;

import com.role.entity.Post;
import com.role.excption.ResourceNotFoundException;
import com.role.payload.PostDto;
import com.role.repository.PostRepository;
import com.role.service.PostService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl  implements PostService {
    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
       Post post=mapToEntity(postDto);
        Post newPost = postRepository.save(post);
        PostDto newPostDto = mapToDto(newPost);
        return newPostDto;
    }

    @Override
    public  List<PostDto> getAllPost() {
        List<Post> posts = postRepository.findAll();
         return posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        post.setQuestion(postDto.getQuestion());
        post.setAnswer(postDto.getAnswer());


        //post.setCreatedAt(postDto.getCreatedAt());
        post.setUpdatedAt(new Date());
        Post updatedPost = postRepository.save(post);
        return mapToDto(updatedPost);
    }

    @Override
    public void deletePostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.deleteById(id);
    }

    private PostDto mapToDto(Post newPost) {
        PostDto postDto=new PostDto();
        postDto.setId(newPost.getId());
        postDto.setQuestion(newPost.getQuestion());
        postDto.setAnswer(newPost.getAnswer());

        postDto.setCreatedAt(newPost.getCreatedAt());
        postDto.setUpdatedAt(newPost.getUpdatedAt());
        return postDto;
    }


    private Post mapToEntity(PostDto postDto) {
        Post post = new Post();
        post.setQuestion(postDto.getQuestion());
        post.setAnswer(postDto.getAnswer());
        return post ;
    }
}
