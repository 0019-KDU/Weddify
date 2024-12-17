package com.chiradev.weddify.service;

import com.chiradev.weddify.dto.GuestRequestDTODBIT0019;
import com.chiradev.weddify.dto.GuestResponseDTODBIT0019;
import com.chiradev.weddify.entity.GuestDBIT0019;
import com.chiradev.weddify.entity.RsvpStatus;
import com.chiradev.weddify.mapper.GuestMapperDBIT0019;
import com.chiradev.weddify.repository.GuestRepositoryDBIT0019;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GuestServiceDBIT0019 {

    private final GuestRepositoryDBIT0019 guestRepositoryDBIT0019;

    public GuestResponseDTODBIT0019 addGuest(GuestRequestDTODBIT0019 guestDTO) {
        GuestDBIT0019 guest = GuestMapperDBIT0019.mapToGuestEntity(guestDTO);
        guest.setRsvpStatus(RsvpStatus.PENDING); // Default status
        guest.setInvitationSent(false);
        GuestDBIT0019 savedGuest = guestRepositoryDBIT0019.save(guest);
        return GuestMapperDBIT0019.mapToGuestResponseDTO(savedGuest);
    }

    public GuestResponseDTODBIT0019 updateGuest(Long id, GuestRequestDTODBIT0019 guestDTO) {
        GuestDBIT0019 guest = guestRepositoryDBIT0019.findById(id)
                .orElseThrow(() -> new RuntimeException("Guest not found with id: " + id));

        guest.setName(guestDTO.getName());
        guest.setEmail(guestDTO.getEmail());
        guest.setRsvpStatus(guestDTO.getRsvpStatus());
        guest.setInvitationSent(guestDTO.isInvitationSent());

        GuestDBIT0019 updatedGuest = guestRepositoryDBIT0019.save(guest);
        return GuestMapperDBIT0019.mapToGuestResponseDTO(updatedGuest);
    }

    public void deleteGuest(Long id) {
        GuestDBIT0019 guest = guestRepositoryDBIT0019.findById(id)
                .orElseThrow(() -> new RuntimeException("Guest not found with id: " + id));
        guestRepositoryDBIT0019.delete(guest);
    }

    public void sendRsvpRequest(Long id) {
        GuestDBIT0019 guest = guestRepositoryDBIT0019.findById(id)
                .orElseThrow(() -> new RuntimeException("Guest not found with id: " + id));
        guest.setInvitationSent(true);
        guestRepositoryDBIT0019.save(guest);
        // Here you can add email sending logic if required.
    }


    public List<GuestResponseDTODBIT0019> trackRsvpResponses(Long eventId) {
        List<GuestDBIT0019> guests = guestRepositoryDBIT0019.findAll()
                .stream()
                .filter(guest -> guest.getEventId().equals(eventId))
                .toList();
        return guests.stream()
                .map(GuestMapperDBIT0019::mapToGuestResponseDTO)
                .collect(Collectors.toList());
    }
    public List<GuestResponseDTODBIT0019> getGuestsByEventId(Long eventId) {
        List<GuestDBIT0019> guests = guestRepositoryDBIT0019.findByEventId(eventId); // Assuming a method in repository to fetch guests by eventId
        return guests.stream()
                .map(guest -> new GuestResponseDTODBIT0019(
                        guest.getId(),
                        guest.getEventId(),
                        guest.getName(),
                        guest.getEmail(),
                        guest.getRsvpStatus(),
                        guest.isInvitationSent()
                ))
                .collect(Collectors.toList());
    }

}














