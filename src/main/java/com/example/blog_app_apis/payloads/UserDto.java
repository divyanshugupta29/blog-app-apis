package com.example.blog_app_apis.payloads;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;

    @NotEmpty
    @Size(min = 3,message = "name size should be greater or equal to 3")
    private String name;
    @Email(message = "not a valid email")
    private String email;
    @NotEmpty
    @Size(min = 3,max = 10,message = "password size should be >= 3 and <= 10")
    private String password;
    @NotEmpty
    private String about;
}
