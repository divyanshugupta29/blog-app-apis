package com.example.blog_app_apis.services.impl;

import com.example.blog_app_apis.entity.Category;
import com.example.blog_app_apis.entity.Post;
import com.example.blog_app_apis.entity.User;
import com.example.blog_app_apis.exception.ResourceNotFoundException;
import com.example.blog_app_apis.payloads.PostDto;
import com.example.blog_app_apis.payloads.PostResponse;
import com.example.blog_app_apis.repositories.CategoryRepo;
import com.example.blog_app_apis.repositories.PostRepo;
import com.example.blog_app_apis.repositories.UserRepo;
import com.example.blog_app_apis.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    PostRepo postRepo;
    ModelMapper modelMapper;
    UserRepo userRepo;
    CategoryRepo categoryRepo;

    public PostServiceImpl(PostRepo postRepo, ModelMapper modelMapper, UserRepo userRepo, CategoryRepo categoryRepo) {
        this.postRepo = postRepo;
        this.modelMapper = modelMapper;
        this.userRepo = userRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public PostDto createPost(PostDto postDto, int userId, int catId) {
        User user =userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId));
        Category category = categoryRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("Category","cattegoryId",catId));
        Post post = modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post newPost= postRepo.save(post);
        return modelMapper.map(newPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, int postId) {
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post updatedPost = postRepo.save(post);
        return modelMapper.map(updatedPost,PostDto.class);
    }

    @Override
    public void deletePost(int postId) {
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
        postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNo, Integer pageSize,String sortBy) {

        Pageable pageable = PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
        Page<Post> pagePost= postRepo.findAll(pageable);
        List<Post> postList = pagePost.getContent();
        List<PostDto> postDtoList = new ArrayList<>();
        for(Post post:postList){
            postDtoList.add(modelMapper.map(post,PostDto.class));
        }
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtoList);
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setTotalElement(pagePost.getTotalElements());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }

    @Override
    public PostDto findPostById(int postId) {
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
        return modelMapper.map(post,PostDto.class);
    }

    @Override
    public List<PostDto> findByCategory(int catId) {
        Category category = categoryRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("Category","categoryId",catId));
        List<Post> postList = postRepo.findByCategory(category);
        List<PostDto> postDtoList = new ArrayList<>();
        for(Post post:postList){
            postDtoList.add(modelMapper.map(post,PostDto.class));
        }
        return postDtoList;
    }

    @Override
    public List<PostDto> findByUser(int userId) {
        User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId));
        List<Post> postList = postRepo.findByUser(user);
        List<PostDto> postDtoList = new ArrayList<>();
        for(Post post:postList){
            postDtoList.add(modelMapper.map(post,PostDto.class));
        }
        return postDtoList;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        return null;
    }
}
