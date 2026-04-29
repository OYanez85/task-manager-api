package com.oscar.dto;

import java.time.LocalDateTime;

public class CommentResponse {

    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private Long taskId;
    private Long authorId;
    private String authorName;

    public CommentResponse(Long id, String content, LocalDateTime createdAt, Long taskId, Long authorId, String authorName) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.taskId = taskId;
        this.authorId = authorId;
        this.authorName = authorName;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Long getTaskId() {
        return taskId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public String getAuthorName() {
        return authorName;
    }
}
