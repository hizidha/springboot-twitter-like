package com.devland.assignment.assignment12.applicationuser.model;

import com.devland.assignment.assignment12.applicationuser.model.dto.ApplicationUserResponseDTO;
import com.devland.assignment.assignment12.comment.model.Comment;
import com.devland.assignment.assignment12.post.model.Post;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String username;

    private String password;

    private String name;

    private String profilePicturePath;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @CreationTimestamp
    private Timestamp createdAt;

    public ApplicationUserResponseDTO convertToResponse() {
        return ApplicationUserResponseDTO.builder()
                .id(this.id)
                .name(this.name)
                .username(this.username)
                .build();
    }
}