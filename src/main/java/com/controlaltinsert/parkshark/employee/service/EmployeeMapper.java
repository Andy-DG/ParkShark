package com.controlaltinsert.parkshark.employee.service;

import com.controlaltinsert.parkshark.employee.api.EmployeeDTO;
import com.controlaltinsert.parkshark.employee.domain.Employee;
import com.controlaltinsert.parkshark.support.address.service.AddressMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmployeeMapper {
    private AddressMapper addressMapper;

    public Employee toEntity(EmployeeDTO contactPerson) {
        return new Employee(contactPerson.getFirstName(), contactPerson.getLastName(), addressMapper.toEntity(contactPerson.getAddress()), contactPerson.getPhoneNumber(), contactPerson.getMobilePhoneNumber(), contactPerson.getEmail());
    }

    public EmployeeDTO toDTO(Employee contact) {
        return EmployeeDTO.builder()
                .id(contact.getId())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .address(addressMapper.toDTO(contact.getAddress()))
                .email(contact.getEmail())
                .mobilePhoneNumber(contact.getMobilePhoneNumber())
                .phoneNumber(contact.getPhoneNumber())
                .build();
    }
}
