package com.controlaltinsert.parkshark.division.api.dto;

import com.controlaltinsert.parkshark.employee.api.EmployeeDTO;
import com.controlaltinsert.parkshark.employee.domain.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreateDivisionDTO {

    private String name;

    private String originalName;

    private EmployeeDTO director;

}
