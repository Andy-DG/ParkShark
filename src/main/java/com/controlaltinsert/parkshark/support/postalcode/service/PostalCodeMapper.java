package com.controlaltinsert.parkshark.support.postalcode.service;

import com.controlaltinsert.parkshark.support.postalcode.api.PostalCodeDTO;
import com.controlaltinsert.parkshark.support.postalcode.domain.PostalCode;

public class PostalCodeMapper {
    public PostalCode toEntity(PostalCodeDTO postalCodeDTO) {
        return new PostalCode(
                postalCodeDTO.getZipCode(),
                postalCodeDTO.getCity()
        );
    }

    public PostalCodeDTO toDTO(PostalCode postalCode) {
        return new PostalCodeDTO(
                postalCode.getId(),
                postalCode.getZipCode(),
                postalCode.getCity());
    }

}
