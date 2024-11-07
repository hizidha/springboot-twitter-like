package com.devland.assignment.assignment12.comment;

import com.devland.assignment.assignment12.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}