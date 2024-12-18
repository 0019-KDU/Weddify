package com.chiradev.weddify.service;

import com.chiradev.weddify.dto.VendorDTOBIT0019;
import com.chiradev.weddify.entity.VendorDBIT0019;
import com.chiradev.weddify.exception.ResourceNotFoundException;
import com.chiradev.weddify.mapper.VendorMapperDBIT0019;
import com.chiradev.weddify.repository.VendorRepositoryDBIT0019;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class VendorServiceDBIT0019 {

    private final VendorRepositoryDBIT0019 vendorRepository;

    public VendorDTOBIT0019 createVendor(VendorDTOBIT0019 vendorDTO) {
        log.info("Creating vendor with name: {}", vendorDTO.getName());
        VendorDBIT0019 vendorEntity = VendorMapperDBIT0019.mapToVendorDBIT0019(vendorDTO);
        VendorDBIT0019 savedVendor = vendorRepository.save(vendorEntity);
        return VendorMapperDBIT0019.mapToVendorDto(savedVendor);
    }


    @Transactional(readOnly = true) // ✅ Now Recognized
    public VendorDTOBIT0019 getVendorById(Long id) {
        log.info("Fetching vendor with id: {}", id);
        VendorDBIT0019 vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found with id: " + id));
        return VendorMapperDBIT0019.mapToVendorDto(vendor);
    }

    @Transactional(readOnly = true) // ✅ Now Recognized
    public List<VendorDTOBIT0019> getAllVendors() {
        log.info("Fetching all vendors");
        return vendorRepository.findAll()
                .stream()
                .map(VendorMapperDBIT0019::mapToVendorDto)
                .collect(Collectors.toList());
    }


    public VendorDTOBIT0019 updateVendor(Long id, VendorDTOBIT0019 vendorDTO) {
        log.info("Updating vendor with id: {}", id);
        VendorDBIT0019 existingVendor = vendorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found with id: " + id));

        existingVendor.setName(vendorDTO.getName());
        existingVendor.setVendorType(vendorDTO.getVendorType());
        existingVendor.setNotes(vendorDTO.getNotes());
        existingVendor.setContactDetails(vendorDTO.getContactDetails());
        existingVendor.setPriceRange(vendorDTO.getPriceRange());

        VendorDBIT0019 updatedVendor = vendorRepository.save(existingVendor);
        return VendorMapperDBIT0019.mapToVendorDto(updatedVendor);
    }

    public void deleteVendor(Long id) {
        log.info("Deleting vendor with id: {}", id);
        VendorDBIT0019 vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found with id: " + id));
        vendorRepository.delete(vendor);
    }
}
