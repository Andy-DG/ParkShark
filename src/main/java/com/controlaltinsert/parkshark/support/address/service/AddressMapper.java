package com.controlaltinsert.parkshark.support.address.service;

import com.controlaltinsert.parkshark.support.address.api.AddressDTO;
import com.controlaltinsert.parkshark.support.address.domain.Address;
import com.controlaltinsert.parkshark.support.postalcode.service.PostalCodeMapper;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Setter
public class AddressMapper {
    private  PostalCodeMapper postalCodeMapper;

    public AddressMapper(PostalCodeMapper postalCodeMapper) {
        this.postalCodeMapper = postalCodeMapper;
    }

    public Address toEntity(AddressDTO addressDTO) {
        return new Address(
                addressDTO.getStreetName(),
                addressDTO.getStreetNumber(),
                postalCodeMapper.toEntity(addressDTO.getPostalCodeDTO())
        );
    }

    public AddressDTO toDTO(Address address) {
        return new AddressDTO(
                address.getStreetName(),
                address.getStreetNumber(),
                postalCodeMapper.toDTO(address.getPostalCode()));
    }
}
