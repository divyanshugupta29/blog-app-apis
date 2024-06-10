package com.example.blog_app_apis.repositories;

import com.example.blog_app_apis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
}
