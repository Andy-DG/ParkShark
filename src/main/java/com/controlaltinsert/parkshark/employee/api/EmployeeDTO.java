package com.controlaltinsert.parkshark.employee.api;

import com.controlaltinsert.parkshark.support.address.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class EmployeeDTO {
    private int id;
    private String firstName;
    private String lastName;
    private Address address;
    private String phoneNumber;
    private String mobilePhoneNumber;
    private String email;
}
