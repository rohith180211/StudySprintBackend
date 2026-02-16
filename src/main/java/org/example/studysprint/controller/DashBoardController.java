package org.example.studysprint.controller;

import lombok.RequiredArgsConstructor;
import org.example.studysprint.dto.dashboard.DashboardResponse;
import org.example.studysprint.services.auth.task.TaskServiceInterface;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashBoardController {
    private final TaskServiceInterface taskService;

    @GetMapping
    public DashboardResponse getDashboard(Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        return taskService.getDashboard(userId);
    }
}
