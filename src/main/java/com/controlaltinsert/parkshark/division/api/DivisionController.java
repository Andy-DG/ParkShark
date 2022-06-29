package com.controlaltinsert.parkshark.division.api;

import com.controlaltinsert.parkshark.division.service.DivisionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("division")
@AllArgsConstructor
public class DivisionController {
private DivisionService divisionService;


}
