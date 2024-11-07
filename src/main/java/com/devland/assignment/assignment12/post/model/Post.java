package com.devland.assignment.assignment12.post.model;

import com.devland.assignment.assignment12.applicationuser.model.ApplicationUser;
import com.devland.assignment.assignment12.applicationuser.model.dto.ApplicationUserResponseDTO;
import com.devland.assignment.assignment12.comment.model.Comment;
import com.devland.assignment.assignment12.comment.model.dto.CommentResponseDTO;
import com.devland.assignment.assignment12.post.model.dto.PostCommentResponseDTO;
import com.devland.assignment.assignment12.post.model.dto.PostResponseDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne()
    @JoinColumn(name = "application_user", nullable = false)
    private ApplicationUser user;

    @Column(columnDefinition = "int default 0")
    private int sumOfComment;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    public PostResponseDTO convertToResponse() {
        ApplicationUserResponseDTO applicationUserResponseDTO = this.user.convertToResponse();

        return PostResponseDTO.builder()
                .id(this.id)
                .content(this.content)
                .sumOfComment(this.sumOfComment)
                .user(applicationUserResponseDTO)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }

    public PostCommentResponseDTO convertToResponseWithComment() {
        ApplicationUserResponseDTO applicationUserResponseDTO = this.user.convertToResponse();
        List<CommentResponseDTO> commentForPostResponseDTO = this.comments.stream()
                .map(Comment::convertToResponse)
                .toList();

        return PostCommentResponseDTO.builder()
                .id(this.id)
                .content(this.content)
                .user(applicationUserResponseDTO)
                .sumOfComment(this.sumOfComment)
                .comments(commentForPostResponseDTO)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }
}