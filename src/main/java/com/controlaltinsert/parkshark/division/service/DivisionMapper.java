package com.controlaltinsert.parkshark.division.service;

import com.controlaltinsert.parkshark.division.api.dto.CreateDivisionDTO;
import com.controlaltinsert.parkshark.division.api.dto.DivisionDTO;
import com.controlaltinsert.parkshark.division.domain.Division;
import com.controlaltinsert.parkshark.employee.domain.Employee;
import com.controlaltinsert.parkshark.employee.service.EmployeeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DivisionMapper {
    private EmployeeMapper employeeMapper;

    public Division toEntity(CreateDivisionDTO createDivisionDTO, Employee director, Division headDivision){
        return new Division(createDivisionDTO.getName(), createDivisionDTO.getOriginalName(), director, headDivision);
    }

    public DivisionDTO toSubDivisionDTO(Division division){
        return new DivisionDTO(division.getId(),division.getName(),division.getOriginalName(),employeeMapper.toDTO(division.getDirector()),toDTO(division.getHeadDivision()));
    }


}
