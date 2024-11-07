package com.devland.assignment.assignment12.post.model.dto;

import com.devland.assignment.assignment12.applicationuser.model.dto.ApplicationUserResponseDTO;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDTO {
    private Long id;
    private String content;
    private ApplicationUserResponseDTO user;
    private int sumOfComment;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}