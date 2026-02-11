package org.example.studysprint.services.auth.task;


import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.studysprint.dto.task.CreateTaskRequest;
import org.example.studysprint.dto.task.TaskResponse;
import org.example.studysprint.model.Task;
import org.example.studysprint.model.User;
import org.example.studysprint.repository.TaskRepository;
import org.example.studysprint.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskServiceInterface{
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    @Override
    @Transactional
    public TaskResponse createTask(Long userId, CreateTaskRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Task task = Task.builder()
                .user(user)
                .title(request.getTitle())
                .subject(request.getSubject())
                .completed(false)
                .build();

        Task savedTask = taskRepository.save(task);

        return mapToResponse(savedTask);
    }

    @Override
    public List<TaskResponse> getMyTasks(Long userId) {
        return taskRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional
    public TaskResponse completeTask(Long userId, Long taskId) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!task.getUser().getId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }

        if (!task.isCompleted()) {
            task.setCompleted(true);
            task.setCompletedAt(Instant.now());

            // 🔥 Add points
            User user = task.getUser();
            user.setPoints(user.getPoints() + 10);
        }

        return mapToResponse(task);
    }


    private TaskResponse mapToResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .subject(task.getSubject())
                .completed(task.isCompleted())
                .createdAt(task.getCreatedAt())
                .completedAt(task.getCompletedAt())
                .build();
    }
}
