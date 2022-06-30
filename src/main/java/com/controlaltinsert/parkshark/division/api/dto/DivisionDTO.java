package com.controlaltinsert.parkshark.division.api.dto;

import com.controlaltinsert.parkshark.employee.api.EmployeeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class DivisionDTO {

    private final int id;

    private final String name;

    private final String originalName;

    private final EmployeeDTO director;

    private DivisionDTO headDivision;
}
