package com.chiradev.weddify.controller;

import com.chiradev.weddify.dto.VendorDTOBIT0019;
import com.chiradev.weddify.service.VendorServiceDBIT0019;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
@RequiredArgsConstructor
public class VendorControllerDBIT0019 {

    private final VendorServiceDBIT0019 vendorService;


    @GetMapping("/{vendorId}/availability")
    public ResponseEntity<Void> checkVendorAvailability(@PathVariable Long vendorId) {
        try {
            System.out.println("Checking availability for vendor: " + vendorId);
            boolean isAvailable = vendorService.isVendorAvailable(vendorId);
            if (isAvailable) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(409).build(); // Conflict
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }


    @PostMapping
    public ResponseEntity<VendorDTOBIT0019> createVendor(@Valid @RequestBody VendorDTOBIT0019 vendorDTO) {
        VendorDTOBIT0019 createdVendor = vendorService.createVendor(vendorDTO);
        return new ResponseEntity<>(createdVendor, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorDTOBIT0019> getVendorById(@PathVariable Long id) {
        VendorDTOBIT0019 vendorDTO = vendorService.getVendorById(id);
        return ResponseEntity.ok(vendorDTO);
    }

    @GetMapping
    public ResponseEntity<List<VendorDTOBIT0019>> getAllVendors() {
        List<VendorDTOBIT0019> vendors = vendorService.getAllVendors();
        return ResponseEntity.ok(vendors);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendorDTOBIT0019> updateVendor(@PathVariable Long id,
                                                         @Valid @RequestBody VendorDTOBIT0019 vendorDTO) {
        VendorDTOBIT0019 updatedVendor = vendorService.updateVendor(id, vendorDTO);
        return ResponseEntity.ok(updatedVendor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendor(id);
        return ResponseEntity.noContent().build();
    }
}
