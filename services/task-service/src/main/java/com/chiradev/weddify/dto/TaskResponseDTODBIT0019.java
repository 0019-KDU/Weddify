package com.chiradev.weddify.dto;

import com.chiradev.weddify.entity.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskResponseDTODBIT0019 {
    private Long id;
    private Long eventId;
    private String description;
    private String assignedTo;
    private TaskStatus status;
    private LocalDate dueDate;
}
