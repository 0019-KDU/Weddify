package com.chiradev.weddify.controller;

import com.chiradev.weddify.dto.TaskDTODBIT0019;
import com.chiradev.weddify.dto.TaskResponseDTODBIT0019;
import com.chiradev.weddify.entity.TaskStatus;
import com.chiradev.weddify.service.TaskServiceDBIT0019;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskControllerDBIT0019 {
    private final TaskServiceDBIT0019 taskService;


    @PostMapping
    public ResponseEntity<TaskResponseDTODBIT0019> createTask(@Valid @RequestBody TaskDTODBIT0019 taskDTO) {
        TaskResponseDTODBIT0019 createdTask = taskService.createTask(taskDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTODBIT0019> getTaskById(@PathVariable Long id) {
        TaskResponseDTODBIT0019 task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTODBIT0019> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskDTODBIT0019 taskDTO) {
        TaskResponseDTODBIT0019 updatedTask = taskService.updateTask(id, taskDTO);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<TaskResponseDTODBIT0019>> getTasksByStatus(@PathVariable TaskStatus status) {
        List<TaskResponseDTODBIT0019> tasks = taskService.getTasksByStatus(status);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<TaskResponseDTODBIT0019>> getTasksByEventId(@PathVariable Long eventId) {
        List<TaskResponseDTODBIT0019> tasks = taskService.getTasksByEventId(eventId);
        return ResponseEntity.ok(tasks);
    }
}
