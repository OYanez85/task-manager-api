package com.oscar.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateCommentRequest {

    @NotBlank(message = "Comment content is required")
    @Size(max = 1000, message = "Comment content must not exceed 1000 characters")
    private String content;

    @NotNull(message = "Author id is required")
    private Long authorId;

    public String getContent() {
        return content;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
}
