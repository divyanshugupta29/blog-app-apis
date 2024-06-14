package com.example.blog_app_apis.controllers;

import com.example.blog_app_apis.entity.Comment;
import com.example.blog_app_apis.payloads.ApiResponse;
import com.example.blog_app_apis.payloads.CommentDTO;
import com.example.blog_app_apis.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {

    CommentService commentService;
    ModelMapper modelMapper;

    @Autowired
    public CommentController(CommentService commentService,ModelMapper modelMapper) {
        this.commentService = commentService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO comment,@PathVariable int postId){
        CommentDTO commentDTO = commentService.creatateComment(comment,postId);
        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> createComment(@PathVariable int commentId){
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("Comment deleted Successfully",true),HttpStatus.OK);
    }
}
