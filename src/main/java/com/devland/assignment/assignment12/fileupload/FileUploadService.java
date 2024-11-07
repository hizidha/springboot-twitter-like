package com.devland.assignment.assignment12.fileupload;

import com.devland.assignment.assignment12.applicationuser.model.ApplicationUser;
import com.devland.assignment.assignment12.fileupload.exception.FileStorageException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileUploadService {
    public String savedPhoto(ApplicationUser newUser, MultipartFile file) {
        String fileName = file.getOriginalFilename();
        try {
            Path uploadPath = Paths.get("uploads");
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            assert fileName != null;
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException e) {
            throw new FileStorageException("Could not store file " + fileName + ", Please try again later.");
        }
    }
}