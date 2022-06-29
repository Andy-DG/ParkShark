package com.controlaltinsert.parkshark.parkinglot.api.dto;


import com.controlaltinsert.parkshark.employee.Employee;
import com.controlaltinsert.parkshark.parkinglot.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ParkingLotDTO {

    private int id;
    private String name;
    private Category category;
    private int maxCapacity;
    private double pricePerHour;
    private Employee contactPerson;
}
