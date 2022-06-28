package com.controlaltinsert.parkshark.support.address.service;

import com.controlaltinsert.parkshark.support.address.api.AddressDTO;
import com.controlaltinsert.parkshark.support.address.domain.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    private final PostalCodeMapper postalCodeMapper;

    public AddressMapper(PostalCodeMapper postalCodeMapper) {
        this.postalCodeMapper = postalCodeMapper;
    }

    public Address toEntity(AddressDTO addressDTO) {
        return new Address(
                addressDTO.getStreetName(),
                addressDTO.getStreetNumber(),
                postalCodeMapper.toEntity(addressDTO.getPostalCode())
        );
    }

    public AddressDTO toDTO(Address address) {
        return AddressDTO(
                address.getStreetName(),
                address.getStreetNumber(),
                postalCodeMapper.toDTO(address.getPostalCode()));
    }
}
