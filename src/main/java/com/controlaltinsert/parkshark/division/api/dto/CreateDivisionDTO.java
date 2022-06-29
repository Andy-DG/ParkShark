package com.controlaltinsert.parkshark.division.api.dto;

import com.controlaltinsert.parkshark.employee.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreateDivisionDTO {

    private String name;

    private String originalName;

    private Employee director;

}
