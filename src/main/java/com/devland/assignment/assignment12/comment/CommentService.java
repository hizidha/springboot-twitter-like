package com.devland.assignment.assignment12.comment;

import com.devland.assignment.assignment12.applicationuser.ApplicationUserService;
import com.devland.assignment.assignment12.applicationuser.model.ApplicationUser;
import com.devland.assignment.assignment12.comment.model.Comment;
import com.devland.assignment.assignment12.post.PostService;
import com.devland.assignment.assignment12.post.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ApplicationUserService applicationUserService;
    private final PostService postService;

    public Comment create(Comment newComment) {
        Post existingPost = this.postService.findBy(newComment.getPost().getId());
        ApplicationUser existingUser = this.applicationUserService.findBy(newComment.getUser().getId());

        existingPost.setSumOfComment(existingPost.getSumOfComment() + 1);
        newComment.setUser(existingUser);
        newComment.setPost(existingPost);

        return this.commentRepository.save(newComment);
    }
}