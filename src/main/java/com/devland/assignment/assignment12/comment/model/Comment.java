package com.devland.assignment.assignment12.comment.model;

import com.devland.assignment.assignment12.applicationuser.model.ApplicationUser;
import com.devland.assignment.assignment12.applicationuser.model.dto.ApplicationUserResponseDTO;
import com.devland.assignment.assignment12.comment.model.dto.CommentResponseDTO;
import com.devland.assignment.assignment12.post.model.Post;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne()
    @JoinColumn(name = "application_user", nullable = false)
    private ApplicationUser user;

    @ManyToOne()
    @JoinColumn(name = "post", nullable = false)
    private Post post;

    @CreationTimestamp
    private Timestamp createdAt;

    public CommentResponseDTO convertToResponse() {
        ApplicationUserResponseDTO applicationUserResponse = this.user.convertToResponse();

        return CommentResponseDTO.builder()
                .id(this.id)
                .content(this.content)
                .user(applicationUserResponse)
                .createdAt(this.createdAt)
                .build();
    }
}