package com.oscar.dto;

import com.oscar.entity.TaskPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class CreateTaskRequest {

    @NotBlank(message = "Task title is required")
    @Size(max = 150, message = "Task title must not exceed 150 characters")
    private String title;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private String description;

    @NotNull(message = "Priority is required")
    private TaskPriority priority;

    private LocalDate deadline;

    @NotNull(message = "Project id is required")
    private Long projectId;

    @NotNull(message = "Assignee id is required")
    private Long assigneeId;

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public TaskPriority getPriority() { return priority; }
    public LocalDate getDeadline() { return deadline; }
    public Long getProjectId() { return projectId; }
    public Long getAssigneeId() { return assigneeId; }

    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setPriority(TaskPriority priority) { this.priority = priority; }
    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }
    public void setAssigneeId(Long assigneeId) { this.assigneeId = assigneeId; }
}
