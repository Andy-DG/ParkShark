package com.controlaltinsert.parkshark.division.api.dto;

import com.controlaltinsert.parkshark.employee.api.EmployeeDTO;
import com.controlaltinsert.parkshark.employee.domain.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@AllArgsConstructor
@Data
public class CreateDivisionDTO {

    private String name;

    private String originalName;

    private EmployeeDTO director;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateDivisionDTO)) return false;
        CreateDivisionDTO that = (CreateDivisionDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(originalName, that.originalName) && Objects.equals(director, that.director);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, originalName, director);
    }
}
