package com.controlaltinsert.parkshark.member.api.dto;

import com.controlaltinsert.parkshark.member.level.domain.MembershipLevel;
import com.controlaltinsert.parkshark.support.address.api.AddressDTO;
import com.controlaltinsert.parkshark.support.address.domain.Address;
import com.controlaltinsert.parkshark.support.licenseplate.api.LicensePlateDTO;
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
    AddressDTO addressDTO;
    LocalDate registrationDate;
    LicensePlateDTO licensePlateDTO;
    MembershipLevel membershipLevel;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemberDTO memberDTO)) return false;
        return Objects.equals(getFirstName(), memberDTO.getFirstName()) && Objects.equals(getLastName(), memberDTO.getLastName()) && Objects.equals(getMobile(), memberDTO.getMobile()) && Objects.equals(getPhone(), memberDTO.getPhone()) && Objects.equals(getEmail(), memberDTO.getEmail()) && Objects.equals(getRegistrationDate(), memberDTO.getRegistrationDate()) && getMembershipLevel() == memberDTO.getMembershipLevel();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getMobile(), getPhone(), getEmail(), getRegistrationDate(), getMembershipLevel());
    }
}
