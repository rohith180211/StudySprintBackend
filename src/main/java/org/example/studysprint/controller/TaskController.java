package org.example.studysprint.controller;

import lombok.RequiredArgsConstructor;
import org.example.studysprint.dto.task.CreateTaskRequest;
import org.example.studysprint.dto.task.TaskResponse;
import org.example.studysprint.services.auth.task.TaskServiceInterface;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskServiceInterface taskService;

    @PostMapping
    public TaskResponse createTask(
            @RequestBody CreateTaskRequest request,
            Authentication authentication
    ) {
        Long userId = (Long) authentication.getPrincipal();
        return taskService.createTask(userId, request);
    }

    @GetMapping
    public List<TaskResponse> getMyTasks(Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        return taskService.getMyTasks(userId);
    }

    @PutMapping("/{taskId}/complete")
    public TaskResponse completeTask(
            @PathVariable Long taskId,
            Authentication authentication
    ) {
        Long userId = (Long) authentication.getPrincipal();
        return taskService.completeTask(userId, taskId);
    }
}
