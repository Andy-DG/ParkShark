package com.controlaltinsert.parkshark.parkinglot.service;

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


    public ParkingLot toEntity(CreateParkingLotDTO createParkingLotDTO) {


        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName(createParkingLotDTO.getName());
        parkingLot.setCategory(createParkingLotDTO.getCategory());
        parkingLot.setContactPerson(employeeMapper.toEntity(createParkingLotDTO.getContactPerson()));
        parkingLot.setMaxCapacity(createParkingLotDTO.getMaxCapacity());
        parkingLot.setPricePerHour(createParkingLotDTO.getPricePerHour());

        return parkingLot;
    }

    public ParkingLotDTO toDTO(ParkingLot parkingLot) {
        return new ParkingLotDTO(parkingLot.getId(), parkingLot.getName(), parkingLot.getCategory(), parkingLot.getMaxCapacity(), parkingLot.getPricePerHour(), employeeMapper.toDTO(parkingLot.getContactPerson()));
    }
}
