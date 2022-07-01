package com.controlaltinsert.parkshark.employee.service;

import com.controlaltinsert.parkshark.employee.api.EmployeeDTO;
import com.controlaltinsert.parkshark.employee.domain.Employee;
import com.controlaltinsert.parkshark.employee.domain.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class EmployeeService {

    private final Logger employeeServiceLogger = LoggerFactory.getLogger(EmployeeService.class);
    EmployeeMapper employeeMapper;
    EmployeeRepository employeeRepository;



    public EmployeeDTO getEmployeeById(int employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        assertEmployeeExists(employee);
        return employeeMapper.toDTO(employee);
    }

    private void assertEmployeeExists(Employee employee) {
        if (employee == null) {
            String errorMessage = "Employee not found!";
            employeeServiceLogger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
