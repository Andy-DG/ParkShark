package com.controlaltinsert.parkshark.parkinglot.service;

import com.controlaltinsert.parkshark.division.domain.Division;
import com.controlaltinsert.parkshark.division.service.DivisionService;
import com.controlaltinsert.parkshark.employee.domain.Employee;
import com.controlaltinsert.parkshark.employee.service.EmployeeService;
import com.controlaltinsert.parkshark.parkinglot.api.dto.CreateParkingLotDTO;
import com.controlaltinsert.parkshark.parkinglot.api.dto.ParkingLotDTO;
import com.controlaltinsert.parkshark.parkinglot.api.dto.ParkingLotListDTO;
import com.controlaltinsert.parkshark.parkinglot.domain.ParkingLot;
import com.controlaltinsert.parkshark.parkinglot.domain.ParkingLotRepository;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Setter
public class ParkingLotService {
    private final Logger parkingLotLogger = LoggerFactory.getLogger(ParkingLotService.class);
    private ParkingLotMapper parkingLotMapper;
    private ParkingLotRepository parkingLotRepository;
    private EmployeeService employeeService;
    private DivisionService divisionService;


    public ParkingLotDTO createParkingLot(CreateParkingLotDTO createParkingLotDTO) {
        ParkingLot parkingLot = this.parkingLotRepository.save(getEntity(createParkingLotDTO));
        return getDTO(parkingLot);
    }

    private ParkingLotDTO getDTO(ParkingLot parkingLot) {
        return this.parkingLotMapper.toDTO(parkingLot);
    }

    private ParkingLot getEntity(CreateParkingLotDTO createParkingLotDTO) {
        Employee contactPerson = employeeService.getEmployeeById(createParkingLotDTO.getContactPersonId());
        Division division = divisionService.getDivisionById(createParkingLotDTO.getDivisionId());
        return this.parkingLotMapper.toEntity(createParkingLotDTO, contactPerson, division);
    }

    public ParkingLotDTO getParkingLotById(int parkingLotId) {
        ParkingLot parkingLot = parkingLotRepository.findById(parkingLotId).orElse(null);
        assertParkingLotExists(parkingLot);
        return parkingLotMapper.toDTO(parkingLot);
    }

    private void assertParkingLotExists(ParkingLot parkingLot) {
        if (parkingLot == null) {
            String errorMessage = "Parking lot not found!";
            parkingLotLogger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public List<ParkingLotListDTO> viewAllParkingLots() {
        List<ParkingLot> parkingLots = parkingLotRepository.findAll();
        return parkingLotMapper.toDTO(parkingLots);
    }
}
