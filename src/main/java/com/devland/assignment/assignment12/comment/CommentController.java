package com.devland.assignment.assignment12.comment;

import com.devland.assignment.assignment12.comment.model.Comment;
import com.devland.assignment.assignment12.comment.model.dto.CommentRequestDTO;
import com.devland.assignment.assignment12.comment.model.dto.CommentResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/posts/{id}/comments")
    public ResponseEntity<CommentResponseDTO> createComment(
            @RequestBody @Valid CommentRequestDTO commentRequestDTO
    ) {
        Comment newComment = commentRequestDTO.convertToEntity();

        Comment savedComment = this.commentService.create(newComment);
        CommentResponseDTO commentResponseDTO = savedComment.convertToResponse();

        return ResponseEntity.status(HttpStatus.CREATED).body(commentResponseDTO);
    }
}