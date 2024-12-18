package com.chiradev.weddify.service;

import com.chiradev.weddify.client.GuestClient;
import com.chiradev.weddify.client.GuestDto;
import com.chiradev.weddify.dto.EventDtoDBIT0019;
import com.chiradev.weddify.entity.EventDBIT0019;
import com.chiradev.weddify.exception.ResourceNotFoundException;
import com.chiradev.weddify.mapper.EventMapperDBIT0019;
import com.chiradev.weddify.repository.EventRepositoryDBIT0019;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.security.PublicKey;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventServiceDBIT0019 {

    private  EventRepositoryDBIT0019 eventRepositoryDBIT0019;
    private GuestClient guestClient;
    private final WebClient vendorWebClient;

    public EventDtoDBIT0019 createEvent(EventDtoDBIT0019 eventDtoDBIT0019) {
        // Mapping DTO to Entity
        EventDBIT0019 eventDBIT0019 = EventMapperDBIT0019.mapToEventDBI0019(eventDtoDBIT0019);

        // Validate Vendor Availability
        try {
            vendorWebClient.get()
                    .uri("/{vendorId}/availability", eventDtoDBIT0019.getVendorId())
                    .retrieve()
                    .toBodilessEntity()
                    .block(); // Synchronous call

            // If no exception, vendor is available
        } catch (WebClientResponseException e) {
            if (e.getStatusCode() == HttpStatus.CONFLICT) {
                // Vendor not available
                throw new IllegalStateException("Vendor with ID " + eventDtoDBIT0019.getVendorId() + " is not available.");
            } else if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                // Vendor does not exist
                throw new ResourceNotFoundException("Vendor with ID " + eventDtoDBIT0019.getVendorId() + " not found.");
            } else {
                // Other errors
                throw new RuntimeException("Failed to validate vendor availability.", e);
            }
        }

        // Saving to the database
        EventDBIT0019 savedEvent = eventRepositoryDBIT0019.save(eventDBIT0019);

        // Mapping Entity back to DTO
        return EventMapperDBIT0019.mapToEventDto(savedEvent);
    }

    // Get event by ID with specific guests for that event
    public EventDtoDBIT0019 getEventById(Long eventId) {
        // Fetch event from DB
        EventDBIT0019 event = eventRepositoryDBIT0019.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with ID: " + eventId));

        // Convert event to DTO
        EventDtoDBIT0019 eventDto = EventMapperDBIT0019.mapToEventDto(event);

        // Fetch guests specific to this event using the GuestClient
        List<GuestDto> guests = guestClient.getGuestsByEventId(eventId);  // Fetch guests based on eventId
        eventDto.setGuests(guests);

        return eventDto;
    }

    // Get all events with specific guest lists
    public List<EventDtoDBIT0019> getAllEvents() {
        // Fetch all events
        List<EventDBIT0019> events = eventRepositoryDBIT0019.findAll();

        // Map events to DTOs and enrich with guest information
        return events.stream()
                .map(event -> {
                    EventDtoDBIT0019 eventDto = EventMapperDBIT0019.mapToEventDto(event);

                    // Fetch guests specific to this event
                    List<GuestDto> guests = guestClient.getGuestsByEventId(event.getEventId()); // Call Feign Client to get event-specific guests
                    eventDto.setGuests(guests);

                    return eventDto;
                })
                .collect(Collectors.toList());
    }
    public EventDtoDBIT0019 updateEvent(Long eventId, EventDtoDBIT0019 updateEvent) {
        EventDBIT0019 event = eventRepositoryDBIT0019.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event does not exist with given ID: " + eventId));

        // Validate Vendor Availability if vendorId is being updated
        if (updateEvent.getVendorId() != null && !updateEvent.getVendorId().equals(event.getVendorId())) {
            try {
                vendorWebClient.get()
                        .uri("/{vendorId}/availability", updateEvent.getVendorId())
                        .retrieve()
                        .toBodilessEntity()
                        .block(); // Synchronous call

                // Vendor is available
                event.setVendorId(updateEvent.getVendorId());
            } catch (WebClientResponseException e) {
                if (e.getStatusCode() == HttpStatus.CONFLICT) {
                    throw new IllegalStateException("Vendor with ID " + updateEvent.getVendorId() + " is not available.");
                } else if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                    throw new ResourceNotFoundException("Vendor with ID " + updateEvent.getVendorId() + " not found.");
                } else {
                    throw new RuntimeException("Failed to validate vendor availability.", e);
                }
            }
        }

        event.setEventName(updateEvent.getEventName());
        event.setEventDate(updateEvent.getEventDate());
        event.setVenue(updateEvent.getVenue());
        event.setEventType(updateEvent.getEventType());

        EventDBIT0019 updatedEventObj = eventRepositoryDBIT0019.save(event);

        return EventMapperDBIT0019.mapToEventDto(updatedEventObj);
    }


    public void deleteEvent(Long eventId){
        EventDBIT0019 event=eventRepositoryDBIT0019.findById(eventId)
                .orElseThrow(()->new ResourceNotFoundException("Event is not exists with given id :" + eventId));

        eventRepositoryDBIT0019.deleteById(eventId);
    }

}
