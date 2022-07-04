package com.controlaltinsert.parkshark.support.address.service;

import com.controlaltinsert.parkshark.support.address.api.AddressDTO;
import com.controlaltinsert.parkshark.support.address.api.CreateAddressDTO;
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

    public Address toEntity(CreateAddressDTO createAddressDTO) {
        return new Address(
                createAddressDTO.getStreetName(),
                createAddressDTO.getStreetNumber(),
                postalCodeMapper.toEntity(createAddressDTO.getPostalCodeDTO())
        );
    }

    public Address toEntity(AddressDTO addressDTO) {
        return new Address(
                addressDTO.getId(),
                addressDTO.getStreetName(),
                addressDTO.getStreetNumber(),
                postalCodeMapper.toEntity(addressDTO.getPostalCodeDTO())
        );
    }

    public AddressDTO toDTO(Address address) {
        return new AddressDTO(
                address.getId(),
                address.getStreetName(),
                address.getStreetNumber(),
                postalCodeMapper.toDTO(address.getPostalCode()));
    }
}
