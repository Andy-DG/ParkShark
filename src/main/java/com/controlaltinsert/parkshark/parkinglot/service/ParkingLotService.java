package com.controlaltinsert.parkshark.parkinglot.service;

import com.controlaltinsert.parkshark.employee.domain.Employee;
import com.controlaltinsert.parkshark.employee.service.EmployeeService;
import com.controlaltinsert.parkshark.parkinglot.api.dto.CreateParkingLotDTO;
import com.controlaltinsert.parkshark.parkinglot.api.dto.ParkingLotDTO;
import com.controlaltinsert.parkshark.parkinglot.domain.ParkingLot;
import com.controlaltinsert.parkshark.parkinglot.domain.ParkingLotRepository;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
@Setter
public class ParkingLotService {
    private ParkingLotMapper parkingLotMapper;
    private ParkingLotRepository parkingLotRepository;

    private EmployeeService employeeService;


    public ParkingLotDTO createParkingLot(CreateParkingLotDTO createParkingLotDTO) {
        ParkingLot parkingLot = this.parkingLotRepository.save(getEntity(createParkingLotDTO));
        return getDTO(parkingLot);
    }

    private ParkingLotDTO getDTO(ParkingLot parkingLot) {
        return this.parkingLotMapper.toDTO(parkingLot);
    }

    private ParkingLot getEntity(CreateParkingLotDTO createParkingLotDTO) {
        Employee contactPerson = employeeService.getEmployeeById(createParkingLotDTO.getContactPersonId());
        return this.parkingLotMapper.toEntity(createParkingLotDTO, contactPerson);
    }
}
