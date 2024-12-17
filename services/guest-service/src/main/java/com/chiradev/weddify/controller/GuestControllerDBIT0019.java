package com.chiradev.weddify.controller;

import com.chiradev.weddify.dto.GuestRequestDTODBIT0019;
import com.chiradev.weddify.dto.GuestResponseDTODBIT0019;
import com.chiradev.weddify.service.GuestServiceDBIT0019;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
