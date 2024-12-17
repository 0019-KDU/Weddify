package com.chiradev.weddify.dto;

import com.chiradev.weddify.entity.RsvpStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GuestRequestDTODBIT0019 {
    private Long eventId;
    private String name;
    private String email;
    private RsvpStatus rsvpStatus;
    private boolean invitationSent;
}
