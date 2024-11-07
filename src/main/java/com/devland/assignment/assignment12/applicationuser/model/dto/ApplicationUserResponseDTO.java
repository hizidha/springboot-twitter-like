package com.devland.assignment.assignment12.applicationuser.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationUserResponseDTO {
    private Long id;
    private String username;
    private String name;
}