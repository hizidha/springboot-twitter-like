package com.devland.assignment.assignment12.post.model.dto;

import com.devland.assignment.assignment12.post.model.Post;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostCommentRequestDTO {
    @Positive(message = "ID must be positive number or not zero")
    @NotNull(message = "ID is required")
    private Long id;

    public Post convertToEntity() {
        return Post.builder()
                .id(this.id)
                .build();
    }
}