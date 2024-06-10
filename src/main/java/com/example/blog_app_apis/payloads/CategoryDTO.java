package com.example.blog_app_apis.payloads;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {

    Integer categoryId;
    @Size(min = 3,message = "category title should be greater than 3")
    @NotEmpty
    String categoryTitle;
    @Size(min = 10,message = "category description should be greater than 10")
    @NotEmpty
    String categoryDescription;
}
