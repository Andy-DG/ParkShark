package com.controlaltinsert.parkshark.support.licenseplate.api;

import lombok.*;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LicensePlateDTO {
    int id;
    String licensePlate;
    String country;
}
