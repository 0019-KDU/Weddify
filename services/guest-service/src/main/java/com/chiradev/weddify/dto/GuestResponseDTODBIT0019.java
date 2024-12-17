package com.chiradev.weddify.dto;

import com.chiradev.weddify.entity.RsvpStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuestResponseDTODBIT0019 {
    private Long id;
    private Long eventId;
    private String name;
    private String email;
    private RsvpStatus rsvpStatus;
    private boolean invitationSent;
}
