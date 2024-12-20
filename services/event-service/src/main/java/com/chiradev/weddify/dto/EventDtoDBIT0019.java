package com.chiradev.weddify.dto;

import com.chiradev.weddify.client.GuestDto;
import com.chiradev.weddify.entity.EventType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDtoDBIT0019 {
    private Long eventId;
    private String eventName;
    private Date eventDate;
    private String venue;
    private EventType eventType;
    private Long vendorId;
    private List<GuestDto> guests;
    private List<TaskResponseDTODBIT0019> tasks;

    // Add a constructor with 5 parameters excluding the guests
    public EventDtoDBIT0019(Long eventId, String eventName, Date eventDate, String venue, EventType eventType) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.venue = venue;
        this.eventType = eventType;
    }
}
