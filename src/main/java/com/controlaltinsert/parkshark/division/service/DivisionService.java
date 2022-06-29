package com.controlaltinsert.parkshark.division.service;

import com.controlaltinsert.parkshark.division.api.dto.CreateDivisionDTO;
import com.controlaltinsert.parkshark.division.api.dto.DivisionDTO;
import com.controlaltinsert.parkshark.division.domain.Division;
import com.controlaltinsert.parkshark.division.domain.DivisionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DivisionService {
    DivisionRepository divisionRepository;
    DivisionMapper divisionMapper;


    public DivisionDTO createDivision(CreateDivisionDTO divisionDTO) {
        Division division = divisionRepository.save(getEntity(divisionDTO));
        return getDto(division);
    }

    private DivisionDTO getDto(Division division) {
        return divisionMapper.toDTO(division);
    }

    private Division getEntity(CreateDivisionDTO divisionDTO) {
        return divisionMapper.toEntity(divisionDTO);
    }
}
