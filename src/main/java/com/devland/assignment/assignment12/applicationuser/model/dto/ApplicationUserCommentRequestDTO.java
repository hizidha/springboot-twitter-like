package com.devland.assignment.assignment12.applicationuser.model.dto;

import com.devland.assignment.assignment12.applicationuser.model.ApplicationUser;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationUserCommentRequestDTO {
    @Positive(message = "ID must be positive number or not zero")
    @NotNull(message = "ID is required")
    private Long id;

    public ApplicationUser convertToEntity() {
        return ApplicationUser.builder()
                .id(this.id)
                .build();
    }
}