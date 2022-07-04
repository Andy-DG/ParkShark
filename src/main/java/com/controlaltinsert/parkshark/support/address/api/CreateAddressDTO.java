package com.controlaltinsert.parkshark.support.address.api;

import com.controlaltinsert.parkshark.support.postalcode.api.PostalCodeDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CreateAddressDTO {
    private String streetName;
    private int streetNumber;
    private PostalCodeDTO postalCodeDTO;
}
