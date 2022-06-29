package com.controlaltinsert.parkshark.division.api.dto;

import com.controlaltinsert.parkshark.employee.api.EmployeeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class DivisionDTO {

    private int id;

    private String name;

    private String originalName;

    private int directorId;
}
