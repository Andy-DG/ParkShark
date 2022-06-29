package com.controlaltinsert.parkshark.division.api;

import com.controlaltinsert.parkshark.division.api.dto.CreateDivisionDTO;
import com.controlaltinsert.parkshark.division.api.dto.DivisionDTO;
import com.controlaltinsert.parkshark.division.service.DivisionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("division")
@AllArgsConstructor
public class DivisionController {
private DivisionService divisionService;

    @PostMapping(path = "/add",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public DivisionDTO createDivision(@RequestBody CreateDivisionDTO divisionDTO) {
        return this.divisionService.createDivision(divisionDTO);
    }
}
