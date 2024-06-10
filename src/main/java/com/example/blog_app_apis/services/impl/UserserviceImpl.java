package com.example.blog_app_apis.services.impl;

import com.example.blog_app_apis.entity.User;
import com.example.blog_app_apis.exception.ResourceNotFoundException;
import com.example.blog_app_apis.payloads.UserDto;
import com.example.blog_app_apis.repositories.UserRepo;
import com.example.blog_app_apis.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserserviceImpl implements UserService {
    UserRepo userRepo;
    ModelMapper modelMapper;

    @Autowired
    public UserserviceImpl(UserRepo userRepo,ModelMapper modelMapper){
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }
    @Override
    public UserDto createUser(UserDto user) {
        User savedUser = userRepo.save(dtoToUser(user));
        return userToUserDTO(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, int userId) {
        User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        User updatedUser = userRepo.save(user);
        return userToUserDTO(updatedUser);
    }

    @Override
    public UserDto getUserById(int userId) {
        User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        return userToUserDTO(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> allUser = userRepo.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for(User user:allUser){
            userDtoList.add(userToUserDTO(user));
        }
        return userDtoList;
    }

    @Override
    public void deleteUser(int userId) {
        User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        userRepo.delete(user);

    }

    private User dtoToUser(UserDto userDto){
        User user = modelMapper.map(userDto,User.class);
        /*user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());*/

        return  user;
    }
    private UserDto userToUserDTO(User user){
        UserDto userDto = modelMapper.map(user,UserDto.class);
        return  userDto;

    }
}
