package com.example.blog_app_apis.controllers;

import com.example.blog_app_apis.entity.Post;
import com.example.blog_app_apis.payloads.ApiResponse;
import com.example.blog_app_apis.payloads.PostDto;
import com.example.blog_app_apis.payloads.PostResponse;
import com.example.blog_app_apis.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {
    PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/users/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable int userId,@PathVariable int categoryId){
        PostDto createdPost = postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable int userId){
        List<PostDto> postDtoList=postService.findByUser(userId);
        return ResponseEntity.ok(postDtoList);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable int categoryId){
        List<PostDto> postDtoList=postService.findByCategory(categoryId);
        return ResponseEntity.ok(postDtoList);
    }

    @GetMapping("/posts")
    ResponseEntity<PostResponse> getAllPost(@RequestParam(name = "pageNo",defaultValue = "0",required = false) Integer pageNo,
                                            @RequestParam(name = "pageSize",defaultValue = "5",required = false)Integer pageSize,
                                            @RequestParam(name = "sortBy",defaultValue = "postId",required = false)String sortBy){
        PostResponse postResponse = postService.getAllPost(pageNo,pageSize,sortBy);
        return ResponseEntity.ok(postResponse);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> findByPostId(@PathVariable int postId){
        PostDto postDto = postService.findPostById(postId);
        return ResponseEntity.ok(postDto);
    }
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable int postId){
        postService.deletePost(postId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("post deleted successfully",true),HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@PathVariable int postId,@RequestBody PostDto postDto){
        PostDto updatePost = postService.updatePost(postDto,postId);
        return new ResponseEntity<>(updatePost,HttpStatus.OK);
    }

    @GetMapping("/posts/search/{keyword}")
    public ResponseEntity<List<PostDto>> searchPost(@PathVariable String keyword){
        List<PostDto> postDtos = postService.searchPosts(keyword);
        return ResponseEntity.ok(postDtos);
    }


}
