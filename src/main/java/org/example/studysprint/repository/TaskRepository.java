package org.example.studysprint.repository;

import org.example.studysprint.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUserId(Long userId);

    List<Task> findByUserIdAndCompletedFalse(Long userId);

    List<Task> findByUserIdAndCompletedTrue(Long userId);
}
