package com.controlaltinsert.parkshark.division.service;

import com.controlaltinsert.parkshark.division.api.dto.CreateDivisionDTO;
import com.controlaltinsert.parkshark.division.api.dto.DivisionDTO;
import com.controlaltinsert.parkshark.division.domain.Division;
import org.springframework.stereotype.Component;

@Component
public class DivisionMapper {
    public Division toEntity(CreateDivisionDTO createDivisionDTO){
        return new Division(createDivisionDTO.getName(), createDivisionDTO.getOriginalName(), createDivisionDTO.getDirector());
    }

    public DivisionDTO toEntity(Division division){
        return new DivisionDTO(division.getId(),division.getName(),division.getOriginalName(),division.getDirector());
    }

}
