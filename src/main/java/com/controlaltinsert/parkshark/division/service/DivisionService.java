package com.controlaltinsert.parkshark.division.service;

import com.controlaltinsert.parkshark.division.domain.DivisionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DivisionService {
    DivisionRepository divisionRepository;
    DivisionMapper divisionMapper;



}
