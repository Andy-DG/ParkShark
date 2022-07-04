package com.controlaltinsert.parkshark.support.address.api;

import com.controlaltinsert.parkshark.support.postalcode.api.PostalCodeDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressDTO {
    int id;
    String streetName;
    int streetNumber;
    PostalCodeDTO postalCodeDTO;
}
