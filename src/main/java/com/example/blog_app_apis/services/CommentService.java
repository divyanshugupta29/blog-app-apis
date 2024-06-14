package com.example.blog_app_apis.services;

import com.example.blog_app_apis.payloads.CommentDTO;

public interface CommentService {
    CommentDTO creatateComment(CommentDTO commentDTO,Integer postId);
    void deleteComment(Integer commentId);
}
