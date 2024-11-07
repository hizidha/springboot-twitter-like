package com.devland.assignment.assignment12.post.model.dto;

import com.devland.assignment.assignment12.applicationuser.model.ApplicationUser;
import com.devland.assignment.assignment12.post.model.Post;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDTO {
    private Long id;
    private ApplicationUser user;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @NotBlank(message = "Content is required")
    private String content;

    public Post convertToEntity() {
        return Post.builder()
                .id(this.id)
                .content(this.content)
                .user(this.user)
                .sumOfComment(0)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }
}