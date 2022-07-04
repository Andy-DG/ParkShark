package com.controlaltinsert.parkshark.employee.api;

import com.controlaltinsert.parkshark.support.address.api.CreateAddressDTO;
import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class EmployeeDTO {
    private int id;
    private String firstName;
    private String lastName;
    private CreateAddressDTO address;
    private String phoneNumber;
    private String mobilePhoneNumber;
    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeDTO)) return false;
        EmployeeDTO that = (EmployeeDTO) o;
        return id == that.id && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(mobilePhoneNumber, that.mobilePhoneNumber) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, phoneNumber, mobilePhoneNumber, email);
    }
}
