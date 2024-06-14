package com.example.blog_app_apis.payloads;

import com.example.blog_app_apis.entity.Category;
import com.example.blog_app_apis.entity.Comment;
import com.example.blog_app_apis.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    private int postId;
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    private CategoryDTO category;
    private UserDto user;
    private Set<CommentDTO> comments;

}
