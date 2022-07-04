package com.controlaltinsert.parkshark.employee.api;

import com.controlaltinsert.parkshark.support.address.api.CreateAddressDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreateEmployeeDTO {
    private String firstName;
    private String lastName;
    private CreateAddressDTO address;
    private String phoneNumber;
    private String mobilePhoneNumber;
    private String email;
}
