package com.controlaltinsert.parkshark.division.service;

import com.controlaltinsert.parkshark.division.api.DivisionDTO;
import com.controlaltinsert.parkshark.division.domain.Division;
import org.springframework.stereotype.Component;

@Component
public class DivisionMapper {
    public Division toDTO(DivisionDTO divisionDTO){
        return new Division().setId(divisionDTO.getId()).setName(divisionDTO.getName()).setO;
    }
}
