package com.controlaltinsert.parkshark.support.address.api;

import com.controlaltinsert.parkshark.support.postalcode.domain.PostalCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AddressDTO {
    private int id;
    private String streetName;
    private int streetNumber;
    private PostalCode postalCode;
}
