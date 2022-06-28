package com.controlaltinsert.parkshark.division.api;

import com.controlaltinsert.parkshark.employee.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class DivisionDTO {

    private Long id;

    private String name;

    private String originalName;

    private Employee director;
}
