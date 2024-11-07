package com.devland.assignment.assignment12.post.exception;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(String message) {
        super(message);
    }
}