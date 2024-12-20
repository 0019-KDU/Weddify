package com.chiradev.weddify.client;

import com.chiradev.weddify.dto.VendorDTOBIT0019;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "vendor-client", url = "http://localhost:8070")
public interface VendorClientDBIT0019 {

    @GetMapping("/api/vendors/{vendorId}")
    VendorDTOBIT0019 getVendorById(@PathVariable("vendorId") Long vendorId);

    @GetMapping("/api/vendors/{vendorId}/availability")
    void checkVendorAvailability(@PathVariable("vendorId") Long vendorId);
}