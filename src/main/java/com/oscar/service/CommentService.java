package com.oscar.service;

import com.oscar.dto.CommentResponse;
import com.oscar.dto.CreateCommentRequest;
import com.oscar.entity.Comment;
import com.oscar.entity.Task;
import com.oscar.entity.User;
import com.oscar.exception.TaskNotFoundException;
import com.oscar.exception.UserNotFoundException;
import com.oscar.repository.CommentRepository;
import com.oscar.repository.TaskRepository;
import com.oscar.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, TaskRepository taskRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public CommentResponse createComment(Long taskId, CreateCommentRequest request) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));

        User author = userRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new UserNotFoundException(request.getAuthorId()));

        Comment comment = new Comment(
                request.getContent(),
                task,
                author
        );

        Comment savedComment = commentRepository.save(comment);

        return mapToCommentResponse(savedComment);
    }

    public List<CommentResponse> getCommentsByTaskId(Long taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new TaskNotFoundException(taskId);
        }

        return commentRepository.findByTaskId(taskId)
                .stream()
                .map(this::mapToCommentResponse)
                .toList();
    }

    private CommentResponse mapToCommentResponse(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getTask().getId(),
                comment.getAuthor().getId(),
                comment.getAuthor().getFullName()
        );
    }
}