package com.chiradev.weddify.controller;

import com.chiradev.weddify.dto.EventDtoDBIT0019;
import com.chiradev.weddify.service.EventServiceDBIT0019;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/event")
public class EventControllerDBIT0019 {

    private EventServiceDBIT0019 eventServiceDBIT0019;

    @PostMapping
    public ResponseEntity<EventDtoDBIT0019>createEvent(@RequestBody EventDtoDBIT0019 eventDtoDBIT0019){
        EventDtoDBIT0019 savedEvent=eventServiceDBIT0019.createEvent(eventDtoDBIT0019);
        return new ResponseEntity<>(savedEvent, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<EventDtoDBIT0019> getEventById(@PathVariable("id") Long eventId){
        EventDtoDBIT0019 eventDtoDBIT0019=eventServiceDBIT0019.getEventById(eventId);
        return ResponseEntity.ok(eventDtoDBIT0019);
    }

    @GetMapping
    public ResponseEntity<List<EventDtoDBIT0019>> getAllEvents(){
        List<EventDtoDBIT0019> events=eventServiceDBIT0019.getAllEvents();
        return ResponseEntity.ok(events);
    }

    public ResponseEntity<EventDtoDBIT0019> updateEvent(@PathVariable("id") Long eventId,
                                                        @RequestBody EventDtoDBIT0019 updateEvent){
        EventDtoDBIT0019 eventDtoDBIT0019=eventServiceDBIT0019.updateEvent(eventId,updateEvent);
        return ResponseEntity.ok(eventDtoDBIT0019);
    }

    public ResponseEntity<String> deleteEvent(@PathVariable("id") Long eventId){
        eventServiceDBIT0019.deleteEvent(eventId);
        return ResponseEntity.ok("Event deleted successfully!");
    }
}
