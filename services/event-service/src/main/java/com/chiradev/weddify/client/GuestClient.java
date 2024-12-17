package com.chiradev.weddify.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@FeignClient(value = "guest-service",url = "http://localhost:8050")
public interface GuestClient {

    @GetMapping("/api/guests")
    List<GuestDto> getAllGuests();

    // New method to get guests by eventId
    @GetMapping("/api/guests/{eventId}")
    List<GuestDto> getGuestsByEventId(@PathVariable("eventId") Long eventId);
}
