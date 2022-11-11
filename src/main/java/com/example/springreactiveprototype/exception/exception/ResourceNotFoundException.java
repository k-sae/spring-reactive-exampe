package com.example.springreactiveprototype.exception.exception;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(String resourceName, String key , Object id){
        super(String.format("%s not found with %s: %s", resourceName, key, id));
    }
}
