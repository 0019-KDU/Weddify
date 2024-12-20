package com.chiradev.weddify.service;

import com.chiradev.weddify.dto.TaskDTODBIT0019;
import com.chiradev.weddify.dto.TaskResponseDTODBIT0019;
import com.chiradev.weddify.entity.TaskDBIT0019;
import com.chiradev.weddify.entity.TaskStatus;
import com.chiradev.weddify.exception.ResourceNotFoundException;
import com.chiradev.weddify.mapper.TaskMapperDBIT0019;
import com.chiradev.weddify.repository.TaskRepositoryDBIT0019;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceDBIT0019 {

    private final TaskRepositoryDBIT0019 taskRepository;


    /**
     * Creates a new task.
     *
     * @param taskDTO Task DTO containing task details.
     * @return Response DTO of the created task.
     */
    public TaskResponseDTODBIT0019 createTask(TaskDTODBIT0019 taskDTO) {
        TaskDBIT0019 taskEntity = TaskMapperDBIT0019.mapToTaskEntity(taskDTO);
        TaskDBIT0019 savedTask = taskRepository.save(taskEntity);
        return TaskMapperDBIT0019.mapToTaskResponseDTO(savedTask);
    }

    /**
     * Retrieves a task by its ID.
     *
     * @param id Task ID.
     * @return Response DTO of the retrieved task.
     */
    public TaskResponseDTODBIT0019 getTaskById(Long id) {
        TaskDBIT0019 task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with ID: " + id));
        return TaskMapperDBIT0019.mapToTaskResponseDTO(task);
    }

    /**
     * Updates an existing task.
     *
     * @param id      Task ID.
     * @param taskDTO Task DTO with updated details.
     * @return Response DTO of the updated task.
     */
    public TaskResponseDTODBIT0019 updateTask(Long id, TaskDTODBIT0019 taskDTO) {
        TaskDBIT0019 existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with ID: " + id));

        // Update the entity fields
        existingTask.setEventId(taskDTO.getEventId());
        existingTask.setDescription(taskDTO.getDescription());
        existingTask.setAssignedTo(taskDTO.getAssignedTo());
        existingTask.setStatus(taskDTO.getStatus());
        existingTask.setDueDate(taskDTO.getDueDate());

        TaskDBIT0019 updatedTask = taskRepository.save(existingTask);
        return TaskMapperDBIT0019.mapToTaskResponseDTO(updatedTask);
    }

    /**
     * Deletes a task by its ID.
     *
     * @param id Task ID.
     */
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new ResourceNotFoundException("Task not found with ID: " + id);
        }
        taskRepository.deleteById(id);
    }

    /**
     * Retrieves tasks by their status.
     *
     * @param status Task status.
     * @return List of response DTOs of the tasks with the specified status.
     */
    public List<TaskResponseDTODBIT0019> getTasksByStatus(TaskStatus status) {
        List<TaskDBIT0019> tasks = taskRepository.findByStatus(status);
        return tasks.stream()
                .map(TaskMapperDBIT0019::mapToTaskResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves tasks by their associated event ID.
     *
     * @param eventId Event ID.
     * @return List of response DTOs of the tasks associated with the specified event ID.
     */
    public List<TaskResponseDTODBIT0019> getTasksByEventId(Long eventId) {
        List<TaskDBIT0019> tasks = taskRepository.findByEventId(eventId);
        return tasks.stream()
                .map(TaskMapperDBIT0019::mapToTaskResponseDTO)
                .collect(Collectors.toList());
    }
}
