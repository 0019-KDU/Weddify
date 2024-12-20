package com.chiradev.weddify.repository;

import com.chiradev.weddify.entity.TaskDBIT0019;
import com.chiradev.weddify.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepositoryDBIT0019 extends JpaRepository<TaskDBIT0019,Long> {
    List<TaskDBIT0019> findByStatus(TaskStatus status);
    List<TaskDBIT0019> findByEventId(Long eventId);
}
