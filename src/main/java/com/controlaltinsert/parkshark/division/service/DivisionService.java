package com.controlaltinsert.parkshark.division.service;

import com.controlaltinsert.parkshark.division.api.dto.CreateDivisionDTO;
import com.controlaltinsert.parkshark.division.api.dto.DivisionDTO;
import com.controlaltinsert.parkshark.division.domain.Division;
import com.controlaltinsert.parkshark.division.domain.DivisionRepository;
import com.controlaltinsert.parkshark.employee.api.EmployeeDTO;
import com.controlaltinsert.parkshark.employee.domain.Employee;
import com.controlaltinsert.parkshark.employee.service.EmployeeService;
import com.controlaltinsert.parkshark.parkinglot.api.dto.ParkingLotListDTO;
import com.controlaltinsert.parkshark.parkinglot.domain.ParkingLot;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
        if (headDivisionById.getFkHeadDivisionId() != 0) {
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
        EmployeeDTO directorDTO = employeeService.getEmployeeById(createDivisionDTO.getDirectorId());
        return divisionMapper.toEntity(createDivisionDTO, directorDTO);
    }

    private DivisionDTO getDTO(Division division) {
        return divisionMapper.toDTO(division);
    }

    public DivisionDTO getDivisionById(int divisionId) {
        Division division = divisionRepository.findById(divisionId).orElse(null);
        assertDivisionExists(division);
        return divisionMapper.toDTO(division);
    }

    private void assertDivisionExists(Division division) {
        if (division == null) {
            String errorMessage = "Division not found!";
            divisionLogger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public List<DivisionDTO> viewAllDivisions() {
        List<Division> divisions = divisionRepository.findAll();
        return divisionMapper.toDTO(divisions);
    }
}
