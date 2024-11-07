package com.devland.assignment.assignment12.post.model.dto;

import com.devland.assignment.assignment12.applicationuser.model.dto.ApplicationUserResponseDTO;
import com.devland.assignment.assignment12.comment.model.dto.CommentResponseDTO;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostCommentResponseDTO {
    private Long id;
    private String content;
    private ApplicationUserResponseDTO user;
    private int sumOfComment;
    private List<CommentResponseDTO> comments;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}