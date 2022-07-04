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
        if (!(o instanceof MemberDTO)) return false;
        MemberDTO memberDTO = (MemberDTO) o;
        return getFk_address_id() == memberDTO.getFk_address_id() && Objects.equals(getFirstName(), memberDTO.getFirstName()) && Objects.equals(getLastName(), memberDTO.getLastName()) && Objects.equals(getMobile(), memberDTO.getMobile()) && Objects.equals(getPhone(), memberDTO.getPhone()) && Objects.equals(getEmail(), memberDTO.getEmail()) && Objects.equals(getRegistrationDate(), memberDTO.getRegistrationDate()) && Objects.equals(getLicensePlate(), memberDTO.getLicensePlate()) && getMembershipLevel() == memberDTO.getMembershipLevel();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getMobile(), getPhone(), getEmail(), getFk_address_id(), getRegistrationDate(), getLicensePlate(), getMembershipLevel());
    }
}
