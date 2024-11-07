package com.devland.assignment.assignment12.post;

import com.devland.assignment.assignment12.post.model.Post;
import com.devland.assignment.assignment12.post.model.dto.PostCommentResponseDTO;
import com.devland.assignment.assignment12.post.model.dto.PostRequestDTO;
import com.devland.assignment.assignment12.post.model.dto.PostResponseDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<Page<PostResponseDTO>> getAll(
            @RequestParam(value = "sort", defaultValue = "ASC") String sortString,
            @RequestParam(value = "order_by", defaultValue = "id") String orderBy,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @RequestParam(value = "page", defaultValue = "1") int page
    ) {
        Page<PostResponseDTO> postResponseDTOs = getPostResponseDTOS(null, sortString, orderBy, limit, page);

        return ResponseEntity.ok(postResponseDTOs);
    }

    @GetMapping("/users/{user_id}/posts/{id}")
    public ResponseEntity<PostCommentResponseDTO> getOne(
            @PathVariable("id") Long id,
            @PathVariable("user_id") Long userId
    ) {
        Post existingPost = this.postService.findBy(id);
        PostCommentResponseDTO postResponseDTO = existingPost.convertToResponseWithComment();

        return ResponseEntity.ok(postResponseDTO);
    }

    @GetMapping("/users/{user_id}/posts")
    public ResponseEntity<Page<PostResponseDTO>> getPostsByUser(
            @PathVariable("user_id") Long userId,
            @RequestParam(value = "sort", defaultValue = "ASC") String sortString,
            @RequestParam(value = "order_by", defaultValue = "id") String orderBy,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @RequestParam(value = "page", defaultValue = "1") int page
    ) {
        Page<PostResponseDTO> postResponseDTOs = getPostResponseDTOS(userId, sortString, orderBy, limit, page);

        return ResponseEntity.ok(postResponseDTOs);
    }

    @PostMapping("/users/{user_id}/posts")
    public ResponseEntity<PostResponseDTO> create(
            @PathVariable("user_id") Long userId,
            @RequestBody @Valid PostRequestDTO postRequestDTO
    ) {
        Post newPost = postRequestDTO.convertToEntity();
        Post savedPost = this.postService.create(newPost, userId);
        PostResponseDTO postResponseDTO = savedPost.convertToResponse();

        return ResponseEntity.status(HttpStatus.CREATED).body(postResponseDTO);
    }

    private Page<PostResponseDTO> getPostResponseDTOS(
            Long user_id,
            String sortString, String orderBy, int limit, int page
    ) {
        Sort sort = Sort.by(Sort.Direction.valueOf(sortString.toUpperCase()), orderBy);
        Pageable pageable = PageRequest.of(page - 1, limit, sort);
        Page<Post> pagePosts = null;

        if (user_id != null) {
            pagePosts = postService.findByUserId(user_id, pageable);
            return pagePosts.map(Post::convertToResponse);
        }

        pagePosts = this.postService.findAll(pageable);
        return pagePosts.map(Post::convertToResponse);
    }
}