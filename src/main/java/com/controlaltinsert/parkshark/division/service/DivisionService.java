package com.controlaltinsert.parkshark.division.service;

import com.controlaltinsert.parkshark.division.api.dto.CreateDivisionDTO;
import com.controlaltinsert.parkshark.division.api.dto.DivisionDTO;
import com.controlaltinsert.parkshark.division.domain.Division;
import com.controlaltinsert.parkshark.division.domain.DivisionRepository;
import com.controlaltinsert.parkshark.employee.api.EmployeeDTO;
import com.controlaltinsert.parkshark.employee.service.EmployeeService;
import com.controlaltinsert.parkshark.util.Validate;
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
        Validate.objectNotNull(headDivisionById, "This head division doesn't exist.");
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


    private Division getEntity(CreateDivisionDTO createDivisionDTO) {
        EmployeeDTO directorDTO = employeeService.getEmployeeById(createDivisionDTO.getDirectorId());
        return divisionMapper.toEntity(createDivisionDTO, directorDTO);
    }

    private DivisionDTO getDTO(Division division) {
        return divisionMapper.toDTO(division);
    }

    public DivisionDTO getDivisionById(int divisionId) {
        Division division = divisionRepository.findById(divisionId).orElse(null);
        Validate.objectNotNull(division, "Division not found!");
        return divisionMapper.toDTO(division);
    }



    public List<DivisionDTO> viewAllDivisions() {
        List<Division> divisions = divisionRepository.findAll();
        return divisionMapper.toDTO(divisions);
    }
}
