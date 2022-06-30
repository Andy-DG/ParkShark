package com.controlaltinsert.parkshark.division.service;

import com.controlaltinsert.parkshark.division.api.dto.CreateDivisionDTO;
import com.controlaltinsert.parkshark.division.api.dto.DivisionDTO;
import com.controlaltinsert.parkshark.division.domain.Division;
import com.controlaltinsert.parkshark.division.domain.DivisionRepository;
import com.controlaltinsert.parkshark.employee.domain.Employee;
import com.controlaltinsert.parkshark.employee.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class DivisionService {
    private final Logger divisionLogger = LoggerFactory.getLogger(DivisionService.class);

    DivisionRepository divisionRepository;
    DivisionMapper divisionMapper;
    EmployeeService employeeService;


    public DivisionDTO createDivision(CreateDivisionDTO divisionDTO) {
        Division division = divisionRepository.save(getDivisionFromDTO(divisionDTO));
        return getDTO(division);
    }

    public DivisionDTO createSubDivision(CreateDivisionDTO subDivisionDTO) {
        Division division = divisionRepository.save(getSubDivisionFromDTO(subDivisionDTO));
        return getDTO(division);
    }

    private Division getSubDivisionFromDTO(CreateDivisionDTO subDivisionDTO) {
        Employee director = employeeService.getEmployeeById(subDivisionDTO.getDirectorId());
        Division headDivision = divisionRepository.findById(subDivisionDTO.getHeadDivisionId()).orElse(null);
        return divisionMapper.subDivisionToEntity(subDivisionDTO, director, headDivision);
    }

    private DivisionDTO getDTO(Division division) {
        return divisionMapper.toDTO(division);
    }

    private Division getDivisionFromDTO(CreateDivisionDTO createDivisionDTO) {
        Employee director = employeeService.getEmployeeById(createDivisionDTO.getDirectorId());
        return divisionMapper.toEntity(createDivisionDTO, director);
    }

    public Division getDivisionById(int divisionId) {
        Division division = divisionRepository.findById(divisionId).orElse(null);
        assertDivisionExists(division);
        return division;
    }

    private void assertDivisionExists(Division division) {
        if (division == null) {
            String errorMessage = "Division not found!";
            divisionLogger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
