package com.oscar.service;

import com.oscar.entity.TaskStatus;
import com.oscar.dto.UpdateTaskStatusRequest;
import com.oscar.dto.CreateTaskRequest;
import com.oscar.dto.TaskResponse;
import com.oscar.entity.Project;
import com.oscar.entity.Task;
import com.oscar.entity.User;
import com.oscar.exception.ProjectNotFoundException;
import com.oscar.exception.TaskNotFoundException;
import com.oscar.exception.UserNotFoundException;
import com.oscar.repository.ProjectRepository;
import com.oscar.repository.TaskRepository;
import com.oscar.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public TaskResponse createTask(CreateTaskRequest request) {
        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new ProjectNotFoundException(request.getProjectId()));

        User assignee = userRepository.findById(request.getAssigneeId())
                .orElseThrow(() -> new UserNotFoundException(request.getAssigneeId()));

        Task task = new Task(
                request.getTitle(),
                request.getDescription(),
                request.getPriority(),
                request.getDeadline(),
                project,
                assignee
        );

        Task savedTask = taskRepository.save(task);

        return mapToTaskResponse(savedTask);
    }

    public List<TaskResponse> getTasks(Long projectId, TaskStatus status) {
        List<Task> tasks;

        if (projectId != null && status != null) {
            tasks = taskRepository.findByProjectIdAndStatus(projectId, status);
        } else if (projectId != null) {
            tasks = taskRepository.findByProjectId(projectId);
        } else if (status != null) {
            tasks = taskRepository.findByStatus(status);
        } else {
            tasks = taskRepository.findAll();
        }

        return tasks.stream()
                .map(this::mapToTaskResponse)
                .toList();
    }

    public TaskResponse getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        return mapToTaskResponse(task);
    }

    public TaskResponse updateTaskStatus(Long id, UpdateTaskStatusRequest request) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        task.setStatus(request.getStatus());

        Task updatedTask = taskRepository.save(task);

        return mapToTaskResponse(updatedTask);
    }

    private TaskResponse mapToTaskResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getDeadline(),
                task.getCreatedAt(),
                task.getProject().getId(),
                task.getProject().getName(),
                task.getAssignee().getId(),
                task.getAssignee().getFullName()
        );
    }
}