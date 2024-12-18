package com.chiradev.weddify.mapper;

import com.chiradev.weddify.dto.VendorDTOBIT0019;
import com.chiradev.weddify.entity.VendorDBIT0019;

public class VendorMapperDBIT0019 {

    public static VendorDTOBIT0019 mapToVendorDto(VendorDBIT0019 vendorDBIT0019){
        return new VendorDTOBIT0019(
                vendorDBIT0019.getId(),
                vendorDBIT0019.getName(),
                vendorDBIT0019.getVendorType(),
                vendorDBIT0019.getNotes(),
                vendorDBIT0019.getContactDetails(),
                vendorDBIT0019.getPriceRange()

        );
    }

    public static VendorDBIT0019 mapToVendorDBIT0019(VendorDTOBIT0019 vendorDTOBIT0019){
        return new VendorDBIT0019(
                vendorDTOBIT0019.getId(),
                vendorDTOBIT0019.getName(),
                vendorDTOBIT0019.getVendorType(),
                vendorDTOBIT0019.getNotes(),
                vendorDTOBIT0019.getContactDetails(),
                vendorDTOBIT0019.getPriceRange()
        );
    }
}
