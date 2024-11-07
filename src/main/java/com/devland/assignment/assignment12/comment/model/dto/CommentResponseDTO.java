package com.devland.assignment.assignment12.comment.model.dto;

import com.devland.assignment.assignment12.applicationuser.model.dto.ApplicationUserResponseDTO;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDTO {
    private Long id;
    private String content;
    private ApplicationUserResponseDTO user;
    private Timestamp createdAt;
}