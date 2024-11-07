package com.devland.assignment.assignment12.authentication.model.dto;

import com.devland.assignment.assignment12.applicationuser.model.ApplicationUser;
import com.devland.assignment.assignment12.comment.model.Comment;
import com.devland.assignment.assignment12.post.model.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequestDTO {
    private Long id;
    private List<Post> posts;
    private List<Comment> comments;
    private Timestamp createdAt;
    private String profilePicturePath;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Pattern(regexp = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Email should be valid")
    private String email;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    public ApplicationUser convertToEntity() {
        return ApplicationUser.builder()
                .id(this.id)
                .email(this.email)
                .username(this.username)
                .password(this.password)
                .name(this.name)
                .posts(this.posts)
                .comments(this.comments)
                .profilePicturePath(this.profilePicturePath)
                .createdAt(this.createdAt)
                .build();
    }
}