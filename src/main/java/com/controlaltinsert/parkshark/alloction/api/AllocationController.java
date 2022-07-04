package com.controlaltinsert.parkshark.alloction.api;

import com.controlaltinsert.parkshark.alloction.api.dto.AllocationDTO;
import com.controlaltinsert.parkshark.alloction.api.dto.CreateAllocationDTO;
import com.controlaltinsert.parkshark.alloction.domain.Allocation;
import com.controlaltinsert.parkshark.alloction.service.AllocationService;
import com.controlaltinsert.parkshark.parkinglot.api.dto.CreateParkingLotDTO;
import com.controlaltinsert.parkshark.parkinglot.api.dto.ParkingLotDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/allocations")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AllocationController {
    AllocationService allocationService;

    @PostMapping(path = "/allocate",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public AllocationDTO allocate(@RequestBody CreateAllocationDTO createAllocationDTO) {
        return this.allocationService.allocate(createAllocationDTO);
    }
}
