package com.example.blog_app_apis.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{
    String reourceName;
    String fieldName;
    int fieldValue;

    public ResourceNotFoundException(String reourceName, String fieldName, int fieldValue) {
        super(String.format("%s not found with %s : %s",reourceName,fieldName,fieldValue));
        this.reourceName = reourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
