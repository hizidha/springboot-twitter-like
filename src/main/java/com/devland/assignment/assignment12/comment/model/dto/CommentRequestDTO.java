package com.devland.assignment.assignment12.comment.model.dto;

import com.devland.assignment.assignment12.applicationuser.model.ApplicationUser;
import com.devland.assignment.assignment12.applicationuser.model.dto.ApplicationUserCommentRequestDTO;
import com.devland.assignment.assignment12.comment.model.Comment;
import com.devland.assignment.assignment12.post.model.Post;
import com.devland.assignment.assignment12.post.model.dto.PostCommentRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDTO {
    private Long id;
    private Timestamp createdAt;

    @NotBlank(message = "Content is required")
    private String content;

    @Valid
    private PostCommentRequestDTO post;

    @Valid
    private ApplicationUserCommentRequestDTO user;

    public Comment convertToEntity() {
        ApplicationUser existingUser = this.user.convertToEntity();
        Post existingPost = this.post.convertToEntity();

        return Comment.builder()
                .id(this.id)
                .content(this.content)
                .post(existingPost)
                .user(existingUser)
                .createdAt(this.createdAt)
                .build();
    }
}