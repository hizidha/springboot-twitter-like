package com.devland.assignment.assignment12.authentication.model.dto;

import com.devland.assignment.assignment12.comment.model.Comment;
import com.devland.assignment.assignment12.post.model.Post;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationResponseDTO {
    private Long id;
    private String email;
    private String username;
    private String password;
    private String name;
    private String profilePicturePath;
    private List<Post> posts;
    private List<Comment> comments;
    private Timestamp createdAt;
}