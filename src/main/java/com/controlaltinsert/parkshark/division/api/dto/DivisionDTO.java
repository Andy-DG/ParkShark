package com.controlaltinsert.parkshark.division.api.dto;

import com.controlaltinsert.parkshark.employee.api.EmployeeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class DivisionDTO {

    private  int id;

    private  String name;

    private  String originalName;

    private  EmployeeDTO director;

    private DivisionDTO headDivision;

    public int getDirectorId() {
        return director.getId();
    }

}
