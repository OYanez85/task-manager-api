package com.oscar.controller;

import com.oscar.dto.CommentResponse;
import com.oscar.dto.CreateCommentRequest;
import com.oscar.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks/{taskId}/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponse createComment(
            @PathVariable Long taskId,
            @Valid @RequestBody CreateCommentRequest request
    ) {
        return commentService.createComment(taskId, request);
    }

    @GetMapping
    public List<CommentResponse> getCommentsByTaskId(@PathVariable Long taskId) {
        return commentService.getCommentsByTaskId(taskId);
    }
}