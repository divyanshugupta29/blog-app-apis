package com.example.blog_app_apis.services.impl;

import com.example.blog_app_apis.entity.Comment;
import com.example.blog_app_apis.entity.Post;
import com.example.blog_app_apis.exception.ResourceNotFoundException;
import com.example.blog_app_apis.payloads.CommentDTO;
import com.example.blog_app_apis.repositories.CommentRepo;
import com.example.blog_app_apis.repositories.PostRepo;
import com.example.blog_app_apis.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private PostRepo postRepo;
    private CommentRepo commentRepo;
    private ModelMapper modelMapper;

    @Autowired
    public CommentServiceImpl(PostRepo postRepo, CommentRepo commentRepo,ModelMapper modelMapper) {
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDTO creatateComment(CommentDTO commentDTO, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","postId",postId));
        Comment comment= modelMapper.map(commentDTO, Comment.class);
        comment.setPost(post);
        Comment savedComment = this.commentRepo.save(comment);
        return modelMapper.map(savedComment,CommentDTO.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","commentId",commentId));
        commentRepo.delete(comment);
    }
}
