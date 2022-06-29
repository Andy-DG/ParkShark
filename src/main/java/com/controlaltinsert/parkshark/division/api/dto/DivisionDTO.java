package com.controlaltinsert.parkshark.division.api.dto;

import com.controlaltinsert.parkshark.employee.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Data
public class DivisionDTO {

    private int id;

    private String name;

    private String originalName;

    private Employee director;
}
