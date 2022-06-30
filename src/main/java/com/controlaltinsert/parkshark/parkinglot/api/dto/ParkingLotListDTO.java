package com.controlaltinsert.parkshark.parkinglot.api.dto;

import com.controlaltinsert.parkshark.division.api.dto.DivisionDTO;
import com.controlaltinsert.parkshark.employee.api.EmployeeDTO;
import com.controlaltinsert.parkshark.parkinglot.domain.Category;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Data
public class ParkingLotListDTO {
    private int id;
    private String name;
    private Category category;
    private int maxCapacity;
    private double pricePerHour;
    private int contactPersonId;
    private int divisionId;
}
