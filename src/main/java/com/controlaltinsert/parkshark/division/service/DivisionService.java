package com.controlaltinsert.parkshark.division.service;

import com.controlaltinsert.parkshark.division.api.dto.CreateDivisionDTO;
import com.controlaltinsert.parkshark.division.api.dto.DivisionDTO;
import com.controlaltinsert.parkshark.division.domain.Division;
import com.controlaltinsert.parkshark.division.domain.DivisionRepository;
import com.controlaltinsert.parkshark.employee.domain.Employee;
import com.controlaltinsert.parkshark.employee.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class DivisionService {
    DivisionRepository divisionRepository;
    DivisionMapper divisionMapper;

    EmployeeService employeeService;


    public DivisionDTO createDivision(CreateDivisionDTO divisionDTO) {
        Division division = divisionRepository.save(getEntity(divisionDTO));
        return getDTO(division);
    }

    private DivisionDTO getDTO(Division division) {
        return divisionMapper.toDTO(division);
    }

    private Division getEntity(CreateDivisionDTO createDivisionDTO) {
        Employee director = employeeService.getEmployeeById(createDivisionDTO.getDirectorId());
        return divisionMapper.toEntity(createDivisionDTO, director);
    }
}
