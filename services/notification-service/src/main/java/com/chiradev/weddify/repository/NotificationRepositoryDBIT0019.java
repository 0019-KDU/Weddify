package com.chiradev.weddify.repository;

import com.chiradev.weddify.entity.NotificationDBIT0019;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotificationRepositoryDBIT0019 extends MongoRepository<NotificationDBIT0019, String> {
    List<NotificationDBIT0019> findByStatus(String status);
}
