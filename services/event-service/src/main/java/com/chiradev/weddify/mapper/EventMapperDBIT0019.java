package com.chiradev.weddify.mapper;

import com.chiradev.weddify.client.GuestDto;
import com.chiradev.weddify.dto.EventDtoDBIT0019;
import com.chiradev.weddify.dto.TaskResponseDTODBIT0019;
import com.chiradev.weddify.entity.EventDBIT0019;

import java.util.List;

public class EventMapperDBIT0019 {

    public static EventDtoDBIT0019 mapToEventDto(EventDBIT0019 eventDBIT0019,
                                                 List<GuestDto> guests,
                                                 List<TaskResponseDTODBIT0019> tasks) {
        return new EventDtoDBIT0019(
                eventDBIT0019.getEventId(),
                eventDBIT0019.getEventName(),
                eventDBIT0019.getEventDate(),
                eventDBIT0019.getVenue(),
                eventDBIT0019.getEventType(),
                eventDBIT0019.getVendorId(),
                guests,
                tasks
        );
    }

    public static EventDBIT0019 mapToEventDBI0019(EventDtoDBIT0019 eventDtoDBIT0019) {
        return new EventDBIT0019(
                eventDtoDBIT0019.getEventId(),
                eventDtoDBIT0019.getEventName(),
                eventDtoDBIT0019.getEventDate(),
                eventDtoDBIT0019.getVenue(),
                eventDtoDBIT0019.getEventType(),
                eventDtoDBIT0019.getVendorId()
        );
    }
}
