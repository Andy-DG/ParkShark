package com.controlaltinsert.parkshark.parkinglot.service;

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


    public ParkingLotDTO createParkingLot(CreateParkingLotDTO createParkingLotDTO) {
        ParkingLot parkingLot = this.parkingLotMapper.toEntity(createParkingLotDTO);
        this.parkingLotRepository.save(parkingLot);
        return this.parkingLotMapper.toDTO(parkingLot);
    }
}
