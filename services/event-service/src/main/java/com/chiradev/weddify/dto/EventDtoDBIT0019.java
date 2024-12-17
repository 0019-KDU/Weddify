package com.chiradev.weddify.dto;

import com.chiradev.weddify.entity.EventType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDtoDBIT0019 {
    private Long eventId;
    private String eventName;
    private Date eventDate;
    private String venue;
    private EventType eventType;
}
