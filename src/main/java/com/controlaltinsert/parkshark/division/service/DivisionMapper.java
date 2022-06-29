package com.controlaltinsert.parkshark.division.service;

import com.controlaltinsert.parkshark.division.api.dto.DivisionDTO;
import com.controlaltinsert.parkshark.division.domain.Division;
import org.springframework.stereotype.Component;

@Component
public class DivisionMapper {
    public Division toDTO(DivisionDTO divisionDTO){
        return new Division(divisionDTO.getName(), divisionDTO.getOriginalName(), divisionDTO.getDirector());
    }

    public DivisionDTO toEntity(Division division){
        return new DivisionDTO(division.getId(),division.getName(),division.getOriginalName(),division.getDirector());
    }
}
