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
        int headDivisionId = divisionDTO.getHeadDivisionId();
        Division headDivisionById = divisionRepository.findById(headDivisionId).orElse(null);
        isNotNull(headDivisionById);
        validateSubDivision(headDivisionById);
        Division division = divisionRepository.save(getEntity(divisionDTO));
        return getDTO(division);
    }

    private void validateSubDivision(Division headDivisionById) {
        if (headDivisionById.getHeadDivisionId() != 0) {
            String errorMessage = "Target head division is a subdivision";
            divisionLogger.error(errorMessage);
            throw new IllegalStateException(errorMessage);
        }
    }

    private void isNotNull(Division headDivisionById) {
        if (headDivisionById == null) {
            String message = "This head division doesn't exist. Creating normal division instead";
            divisionLogger.error(message);
            throw new IllegalArgumentException(message);
        }
    }


    private Division getEntity(CreateDivisionDTO createDivisionDTO) {
        Employee director = employeeService.getEmployeeById(createDivisionDTO.getDirectorId());
        return divisionMapper.toEntity(createDivisionDTO, director);
    }

    private DivisionDTO getDTO(Division division) {
        return divisionMapper.toDTO(division);
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

    private int validateHeadDivisionId(int headDivisionId) {
        if (headDivisionId != 0) {
            throw new IllegalStateException("Target head division is a subdivision");
        }
        return headDivisionId;
    }
}
