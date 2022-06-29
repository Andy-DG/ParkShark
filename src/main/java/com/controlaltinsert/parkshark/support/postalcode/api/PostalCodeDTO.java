package com.controlaltinsert.parkshark.support.postalcode.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PostalCodeDTO {
    private int id;
    private String zipCode;
    private String city;
}
