package org.example.studysprint.services.auth.task;

import org.example.studysprint.dto.dashboard.DashboardResponse;
import org.example.studysprint.dto.task.CreateTaskRequest;
import org.example.studysprint.dto.task.TaskResponse;

import java.util.List;

public interface TaskServiceInterface {
    TaskResponse createTask(Long userId, CreateTaskRequest request);

    List<TaskResponse> getMyTasks(Long userId);

    TaskResponse completeTask(Long userId, Long taskId);

    DashboardResponse getDashboard(Long userId);
}
