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



    public EmployeeDTO getEmployeeById(int directorId) {
        Employee director = employeeRepository.findById(directorId).orElse(null);
        assertDirectorExists(director);
        return employeeMapper.toDTO(director);
    }

    private void assertDirectorExists(Employee director) {
        if (director == null) {
            String errorMessage = "Employee not found!";
            employeeServiceLogger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
