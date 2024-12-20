package com.chiradev.weddify.mapper;


import com.chiradev.weddify.dto.TaskDTODBIT0019;
import com.chiradev.weddify.dto.TaskResponseDTODBIT0019;
import com.chiradev.weddify.entity.TaskDBIT0019;
import org.springframework.stereotype.Component;


public class TaskMapperDBIT0019 {

    /**
     * Converts a Task entity to a TaskResponseDTO.
     *
     * @param task the Task entity
     * @return the TaskResponseDTO
     */
    public static TaskResponseDTODBIT0019 mapToTaskResponseDTO(TaskDBIT0019 task) {
        return new TaskResponseDTODBIT0019(
                task.getId(),
                task.getEventId(),
                task.getDescription(),
                task.getAssignedTo(),
                task.getStatus(),
                task.getDueDate()
        );
    }

    /**
     * Converts a TaskDTO to a Task entity.
     *
     * @param taskDTO the TaskDTO
     * @return the Task entity
     */
    public static TaskDBIT0019 mapToTaskEntity(TaskDTODBIT0019 taskDTO) {
        return new TaskDBIT0019(
                taskDTO.getId(),
                taskDTO.getEventId(),
                taskDTO.getDescription(),
                taskDTO.getAssignedTo(),
                taskDTO.getStatus(),
                taskDTO.getDueDate()
        );
    }

    /**
     * Converts a Task entity to a TaskDTO.
     *
     * @param task the Task entity
     * @return the TaskDTO
     */
    public static TaskDTODBIT0019 mapToTaskDTO(TaskDBIT0019 task) {
        return new TaskDTODBIT0019(
                task.getId(),
                task.getEventId(),
                task.getDescription(),
                task.getAssignedTo(),
                task.getStatus(),
                task.getDueDate()
        );
    }
}
