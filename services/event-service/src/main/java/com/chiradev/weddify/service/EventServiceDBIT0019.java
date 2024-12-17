package com.chiradev.weddify.service;

import com.chiradev.weddify.dto.EventDtoDBIT0019;
import com.chiradev.weddify.entity.EventDBIT0019;
import com.chiradev.weddify.exception.ResourceNotFoundException;
import com.chiradev.weddify.mapper.EventMapperDBIT0019;
import com.chiradev.weddify.repository.EventRepositoryDBIT0019;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventServiceDBIT0019 {

    private  EventRepositoryDBIT0019 eventRepositoryDBIT0019;

    public EventDtoDBIT0019 createEvent(EventDtoDBIT0019 eventDtoDBIT0019) {
        // Mapping DTO to Entity
        EventDBIT0019 eventDBIT0019 = EventMapperDBIT0019.mapToEventDBI0019(eventDtoDBIT0019);

        // Saving to the database
        EventDBIT0019 savedEvent = eventRepositoryDBIT0019.save(eventDBIT0019);

        // Mapping Entity back to DTO
        return EventMapperDBIT0019.mapToEventDto(savedEvent);
    }

    public EventDtoDBIT0019 getEventById(Long eventId){
       EventDBIT0019 eventDBIT0019=   eventRepositoryDBIT0019.findById(eventId)
                .orElseThrow(()->new
                        ResourceNotFoundException("Employee is not exist with given id :" + eventId ));

       return EventMapperDBIT0019.mapToEventDto(eventDBIT0019);
    }

    public List<EventDtoDBIT0019> getAllEvents(){
        List<EventDBIT0019> eventDBIT0019s=eventRepositoryDBIT0019.findAll();
        return eventDBIT0019s.stream().map((EventMapperDBIT0019::mapToEventDto))
                .collect(Collectors.toList());
    }

    public EventDtoDBIT0019 updateEvent(Long eventId,EventDtoDBIT0019 updateEvent){
        EventDBIT0019 event=eventRepositoryDBIT0019.findById(eventId)
                .orElseThrow(()->new ResourceNotFoundException("Event is not exists with given id :" + eventId));

        event.setEventName(updateEvent.getEventName());
        event.setEventDate(updateEvent.getEventDate());
        event.setVenue(updateEvent.getVenue());
        event.setEventType(updateEvent.getEventType());

        EventDBIT0019 updateEventObj=eventRepositoryDBIT0019.save(event);

        return EventMapperDBIT0019.mapToEventDto(updateEventObj);
    }

    public void deleteEvent(Long eventId){
        EventDBIT0019 event=eventRepositoryDBIT0019.findById(eventId)
                .orElseThrow(()->new ResourceNotFoundException("Event is not exists with given id :" + eventId));

        eventRepositoryDBIT0019.deleteById(eventId);
    }


}
