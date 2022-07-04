package com.controlaltinsert.parkshark.parkinglot.service;

import com.controlaltinsert.parkshark.division.api.dto.DivisionDTO;
import com.controlaltinsert.parkshark.division.service.DivisionService;
import com.controlaltinsert.parkshark.employee.api.EmployeeDTO;
import com.controlaltinsert.parkshark.employee.service.EmployeeService;
import com.controlaltinsert.parkshark.parkinglot.api.dto.CreateParkingLotDTO;
import com.controlaltinsert.parkshark.parkinglot.api.dto.ParkingLotDTO;
import com.controlaltinsert.parkshark.parkinglot.api.dto.ParkingLotListDTO;
import com.controlaltinsert.parkshark.parkinglot.domain.ParkingLot;
import com.controlaltinsert.parkshark.parkinglot.domain.ParkingLotRepository;
import com.controlaltinsert.parkshark.util.Validate;
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
        EmployeeDTO contactPersonDTO = employeeService.getEmployeeById(createParkingLotDTO.getContactPersonId());
        DivisionDTO divisionDTO = divisionService.getDivisionById(createParkingLotDTO.getDivisionId());
        return this.parkingLotMapper.toEntity(createParkingLotDTO, contactPersonDTO, divisionDTO);
    }

    public ParkingLotDTO getParkingLotById(int parkingLotId) {
        ParkingLot parkingLot = parkingLotRepository.findById(parkingLotId).orElse(null);
        Validate.objectNotNull(parkingLot, "Parking lot not found.");
        return parkingLotMapper.toDTO(parkingLot);
    }

    public List<ParkingLotListDTO> viewAllParkingLots() {
        List<ParkingLot> parkingLots = parkingLotRepository.findAll();
        return parkingLotMapper.toDTO(parkingLots);
    }
}
