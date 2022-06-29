package com.controlaltinsert.parkshark.division.service;

import com.controlaltinsert.parkshark.division.api.dto.DivisionDTO;
import com.controlaltinsert.parkshark.division.domain.Division;
import org.springframework.stereotype.Component;

@Component
public class DivisionMapper {
    public Division toDTO(DivisionDTO divisionDTO){
        return Division.builder().id()
    }
}
