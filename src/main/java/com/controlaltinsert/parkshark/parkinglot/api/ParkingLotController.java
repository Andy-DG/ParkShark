package com.controlaltinsert.parkshark.parkinglot.api;

import com.controlaltinsert.parkshark.parkinglot.api.dto.CreateParkingLotDTO;
import com.controlaltinsert.parkshark.parkinglot.api.dto.ParkingLotDTO;
import com.controlaltinsert.parkshark.parkinglot.api.dto.ParkingLotListDTO;
import com.controlaltinsert.parkshark.parkinglot.service.ParkingLotService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("parkinglots")
@AllArgsConstructor
public class ParkingLotController {
    private ParkingLotService parkingLotService;

    @PostMapping(path = "/add",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ParkingLotDTO createParkingLot(@RequestBody CreateParkingLotDTO parkingLotDTO) {
        return this.parkingLotService.createParkingLot(parkingLotDTO);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ParkingLotDTO getParkingLot(@PathVariable int id){
        return this.parkingLotService.getParkingLotById(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ParkingLotListDTO> viewAllParkingLots() {
        return this.parkingLotService.viewAllParkingLots();
    }
}
