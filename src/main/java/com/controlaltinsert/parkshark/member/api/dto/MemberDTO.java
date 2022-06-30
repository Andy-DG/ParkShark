package com.controlaltinsert.parkshark.member.api.dto;

import com.controlaltinsert.parkshark.support.licenseplate.domain.LicensePlate;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;



@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberDTO {
    int id;
    String firstName;
    String lastName;
    String mobile;
    String phone;
    String email;
    int fk_address_id;
    LocalDate registrationDate;
    LicensePlate licensePlate;
}
