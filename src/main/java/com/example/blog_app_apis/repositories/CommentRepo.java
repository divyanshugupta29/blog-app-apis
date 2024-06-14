package com.example.blog_app_apis.repositories;

import com.example.blog_app_apis.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
}
