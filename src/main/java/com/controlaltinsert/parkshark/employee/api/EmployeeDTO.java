package com.controlaltinsert.parkshark.employee.api;

import com.controlaltinsert.parkshark.support.address.api.AddressDTO;
import com.controlaltinsert.parkshark.support.address.api.CreateAddressDTO;
import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@EqualsAndHashCode
public class EmployeeDTO {
    private int id;
    private String firstName;
    private String lastName;
    private AddressDTO addressDTO;
    private String phoneNumber;
    private String mobilePhoneNumber;
    private String email;
}
