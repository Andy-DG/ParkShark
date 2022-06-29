package com.controlaltinsert.parkshark.employee.service;

import com.controlaltinsert.parkshark.employee.api.CreateEmployeeDTO;
import com.controlaltinsert.parkshark.employee.api.EmployeeDTO;
import com.controlaltinsert.parkshark.employee.domain.Employee;
import com.controlaltinsert.parkshark.support.address.service.AddressMapper;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Setter
public class EmployeeMapper {
    private AddressMapper addressMapper;

    public Employee toEntity(CreateEmployeeDTO createEmployeeDTO) {
        return new Employee(createEmployeeDTO.getFirstName(), createEmployeeDTO.getLastName(), addressMapper.toEntity(createEmployeeDTO.getAddress()), createEmployeeDTO.getPhoneNumber(), createEmployeeDTO.getMobilePhoneNumber(), createEmployeeDTO.getEmail());
    }


    public EmployeeDTO toDTO(Employee employee) {
        return new EmployeeDTO(employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                addressMapper.toDTO(employee.getAddress()),
                employee.getEmail(),
                employee.getMobilePhoneNumber(),
                employee.getPhoneNumber());
    }

}
