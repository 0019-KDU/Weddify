package com.chiradev.weddify.repository;

import com.chiradev.weddify.entity.GuestDBIT0019;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuestRepositoryDBIT0019 extends JpaRepository<GuestDBIT0019,Long> {
    List<GuestDBIT0019> findByEventId(Long eventId);
}
