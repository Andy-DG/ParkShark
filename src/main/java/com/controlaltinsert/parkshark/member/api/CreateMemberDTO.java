package com.controlaltinsert.parkshark.member.api;

import com.controlaltinsert.parkshark.support.address.api.AddressDTO;
import com.controlaltinsert.parkshark.support.licenseplate.api.CreateLicensePlateDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateMemberDTO {
    String firstName;
    String lastName;
    String mobile;
    String phone;
    String email;
    AddressDTO addressDTO;
    LocalDate registrationDate;
    CreateLicensePlateDTO licensePlateDTO;

}
