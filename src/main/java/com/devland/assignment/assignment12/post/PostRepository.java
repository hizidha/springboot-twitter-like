package com.devland.assignment.assignment12.post;

import com.devland.assignment.assignment12.post.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByUserId(Long userId, Pageable pageable);
}