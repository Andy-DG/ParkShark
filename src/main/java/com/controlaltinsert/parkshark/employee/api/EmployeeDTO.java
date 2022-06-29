package com.controlaltinsert.parkshark.employee.api;

import com.controlaltinsert.parkshark.support.address.api.AddressDTO;
import com.controlaltinsert.parkshark.support.address.domain.Address;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
public class EmployeeDTO {
    private int id;
    private String firstName;
    private String lastName;
    private AddressDTO address;
    private String phoneNumber;
    private String mobilePhoneNumber;
    private String email;
}
