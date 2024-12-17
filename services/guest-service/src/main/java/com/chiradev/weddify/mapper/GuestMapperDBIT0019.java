package com.chiradev.weddify.mapper;

import com.chiradev.weddify.dto.GuestRequestDTODBIT0019;
import com.chiradev.weddify.dto.GuestResponseDTODBIT0019;
import com.chiradev.weddify.entity.GuestDBIT0019;

public class GuestMapperDBIT0019 {
    /**
     * Maps GuestRequestDTO (DTO) to GuestDBIT0019 (Entity).
     * Used for creating or updating guests.
     *
     * @param guestRequestDTO DTO object containing guest data.
     * @return GuestDBIT0019 Entity object representing the guest.
     */
    public static GuestDBIT0019 mapToGuestEntity(GuestRequestDTODBIT0019 guestRequestDTO) {
        if (guestRequestDTO == null) {
            return null; // Safeguard against null input
        }
        return GuestDBIT0019.builder()
                .eventId(guestRequestDTO.getEventId())
                .name(guestRequestDTO.getName())
                .email(guestRequestDTO.getEmail())
                .rsvpStatus(guestRequestDTO.getRsvpStatus())
                .invitationSent(guestRequestDTO.isInvitationSent())
                .build();
    }

    /**
     * Maps GuestDBIT0019 (Entity) to GuestResponseDTO (DTO).
     * Used for returning guest details to the client.
     *
     * @param guestDBIT0019 Entity object containing guest data.
     * @return GuestResponseDTO DTO object representing the guest.
     */
    public static GuestResponseDTODBIT0019 mapToGuestResponseDTO(GuestDBIT0019 guestDBIT0019) {
        if (guestDBIT0019 == null) {
            return null; // Safeguard against null input
        }
        return GuestResponseDTODBIT0019.builder()
                .id(guestDBIT0019.getId())
                .eventId(guestDBIT0019.getEventId())
                .name(guestDBIT0019.getName())
                .email(guestDBIT0019.getEmail())
                .rsvpStatus(guestDBIT0019.getRsvpStatus())
                .invitationSent(guestDBIT0019.isInvitationSent())
                .build();
    }
}
