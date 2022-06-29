package com.controlaltinsert.parkshark.parkinglot.api.dto;

import com.controlaltinsert.parkshark.employee.api.CreateEmployeeDTO;
import com.controlaltinsert.parkshark.employee.api.EmployeeDTO;
import com.controlaltinsert.parkshark.parkinglot.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CreateParkingLotDTO {

    private String name;
    private Category category;
    private int maxCapacity;
    private double pricePerHour;
    private int contactPersonId;
}
