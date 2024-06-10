package com.example.blog_app_apis.services;

import com.example.blog_app_apis.entity.Post;
import com.example.blog_app_apis.payloads.PostDto;
import com.example.blog_app_apis.payloads.PostResponse;

import java.util.List;

public interface PostService {
    //post
    PostDto createPost(PostDto postDto,int userId,int catId);
    //put
    PostDto updatePost(PostDto postDto,int postId);
    //delete
    void deletePost(int postId);
    //getAllPost
    PostResponse getAllPost(Integer pageNo, Integer pageSize,String sortBy);
    //post by id
    PostDto findPostById(int postId);
    //post by category
    List<PostDto> findByCategory(int catId);
    //post by user
    List<PostDto> findByUser(int userId);
    //search post
    List<PostDto> searchPosts(String keyword);

}
