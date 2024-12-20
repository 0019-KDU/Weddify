package com.chiradev.weddify.client;

import com.chiradev.weddify.dto.EventDtoDBIT0019;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "event-client", url = "${services.event-service.url}")
public interface EventClientDBIT0019 {

    @GetMapping("/{eventId}")
    EventDtoDBIT0019 getEventById(@PathVariable("eventId") Long eventId);
}
