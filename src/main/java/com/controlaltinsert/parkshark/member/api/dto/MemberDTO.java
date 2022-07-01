package com.controlaltinsert.parkshark.member.api.dto;

import com.controlaltinsert.parkshark.support.licenseplate.domain.LicensePlate;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;


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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemberDTO)) return false;
        MemberDTO memberDTO = (MemberDTO) o;
        return fk_address_id == memberDTO.fk_address_id && Objects.equals(firstName, memberDTO.firstName) && Objects.equals(lastName, memberDTO.lastName) && Objects.equals(mobile, memberDTO.mobile) && Objects.equals(phone, memberDTO.phone) && Objects.equals(email, memberDTO.email) && Objects.equals(registrationDate, memberDTO.registrationDate) && Objects.equals(licensePlate, memberDTO.licensePlate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, mobile, phone, email, fk_address_id, registrationDate, licensePlate);
    }
}
