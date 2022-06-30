package com.controlaltinsert.parkshark.parkinglot.service;

import com.controlaltinsert.parkshark.division.domain.Division;
import com.controlaltinsert.parkshark.division.service.DivisionMapper;
import com.controlaltinsert.parkshark.employee.domain.Employee;
import com.controlaltinsert.parkshark.employee.service.EmployeeMapper;
import com.controlaltinsert.parkshark.parkinglot.api.dto.CreateParkingLotDTO;
import com.controlaltinsert.parkshark.parkinglot.api.dto.ParkingLotDTO;
import com.controlaltinsert.parkshark.parkinglot.domain.ParkingLot;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Setter
public class ParkingLotMapper {
    private EmployeeMapper employeeMapper;
    private DivisionMapper divisionMapper;


    public ParkingLot toEntity(CreateParkingLotDTO createParkingLotDTO, Employee contactPerson, Division division) {
        return new ParkingLot(
                createParkingLotDTO.getName()
                , createParkingLotDTO.getCategory()
                , createParkingLotDTO.getMaxCapacity()
                , createParkingLotDTO.getPricePerHour()
                , contactPerson
                , division);
    }

    public ParkingLotDTO toDTO(ParkingLot parkingLot) {
        return new ParkingLotDTO(
                parkingLot.getId(),
                parkingLot.getName(),
                parkingLot.getCategory(),
                parkingLot.getMaxCapacity(),
                parkingLot.getPricePerHour(),
                employeeMapper.toDTO(parkingLot.getContactPerson()),
                divisionMapper.toDTO(parkingLot.getDivision()));
    }
}
