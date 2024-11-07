package com.devland.assignment.assignment12.post;

import com.devland.assignment.assignment12.applicationuser.ApplicationUserService;
import com.devland.assignment.assignment12.applicationuser.exception.UserNotFoundException;
import com.devland.assignment.assignment12.applicationuser.model.ApplicationUser;
import com.devland.assignment.assignment12.post.exception.PostNotFoundException;
import com.devland.assignment.assignment12.post.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final ApplicationUserService applicationUserService;

    public Page<Post> findAll(Pageable pageable) {
        return this.postRepository.findAll(pageable);
    }

    public Page<Post> findByUserId(Long userId, Pageable pageable) {
        return this.postRepository.findByUserId(userId, pageable);
    }

    public Post create(Post newPost, Long userId) {
        ApplicationUser existingUser = this.applicationUserService.findBy(userId);
        newPost.setUser(existingUser);

        return this.postRepository.save(newPost);
    }

    public Post findBy(Long postId) {
        return this.postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post with Id " + postId + " not found."));
    }
}