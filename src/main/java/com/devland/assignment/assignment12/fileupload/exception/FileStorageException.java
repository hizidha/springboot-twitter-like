package com.devland.assignment.assignment12.fileupload.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class FileStorageException extends RuntimeException {
    public FileStorageException(String message) {
        super(message);
    }
}