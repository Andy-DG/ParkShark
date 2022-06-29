package com.controlaltinsert.parkshark.employee.api;

import com.controlaltinsert.parkshark.support.address.api.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreateEmployeeDTO {
    private String firstName;
    private String lastName;
    private AddressDTO address;
    private String phoneNumber;
    private String mobilePhoneNumber;
    private String email;
}
