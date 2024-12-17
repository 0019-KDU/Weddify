package com.chiradev.weddify.controller;

import com.chiradev.weddify.dto.GuestRequestDTODBIT0019;
import com.chiradev.weddify.dto.GuestResponseDTODBIT0019;
import com.chiradev.weddify.service.GuestServiceDBIT0019;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guests")
@RequiredArgsConstructor
public class GuestControllerDBIT0019 {

    private final GuestServiceDBIT0019 guestServiceDBIT0019;

    @PostMapping
    public ResponseEntity<GuestResponseDTODBIT0019> addGuest(@RequestBody GuestRequestDTODBIT0019 guestDTO) {
        return ResponseEntity.ok(guestServiceDBIT0019.addGuest(guestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuestResponseDTODBIT0019> updateGuest(
            @PathVariable Long id,
            @RequestBody GuestRequestDTODBIT0019 guestDTO) {
        return ResponseEntity.ok(guestServiceDBIT0019.updateGuest(id, guestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuest(@PathVariable Long id) {
        guestServiceDBIT0019.deleteGuest(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{eventId}")
    public ResponseEntity<List<GuestResponseDTODBIT0019>> getGuestsByEventId(@PathVariable("eventId") Long eventId) {
        List<GuestResponseDTODBIT0019> guests = guestServiceDBIT0019.getGuestsByEventId(eventId);
        return ResponseEntity.ok(guests);
    }
    @PostMapping("/{id}/send-rsvp")
    public ResponseEntity<Void> sendRsvpRequest(@PathVariable Long id) {
        guestServiceDBIT0019.sendRsvpRequest(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/track")
    public ResponseEntity<List<GuestResponseDTODBIT0019>> trackRsvpResponses(@RequestParam Long eventId) {
        return ResponseEntity.ok(guestServiceDBIT0019.trackRsvpResponses(eventId));
    }

}
