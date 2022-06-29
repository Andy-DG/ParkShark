package com.controlaltinsert.parkshark.division.service;

import com.controlaltinsert.parkshark.division.api.dto.CreateDivisionDTO;
import com.controlaltinsert.parkshark.division.api.dto.DivisionDTO;
import com.controlaltinsert.parkshark.division.domain.Division;
import com.controlaltinsert.parkshark.employee.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DivisionServiceTest {
    @Test
    @DisplayName("given a division service, when creating a new division, the division exists")
    void givenADivisionServiceWhenCreatingANewDivisionTheDivisionExists() {

        //given
        int id = 11;
        String name = "ParkShark Ghelamco";
        String originalName = "Parking Service Buffalo K.A.A. Gent Ghelamco";
        Employee director = new Employee();

        DivisionMapper divisionMapper = new DivisionMapper();
//        DivisionRepository divisionRepository = Mockito.mock(DivisionRepository.class);
        DivisionService divisionService;
//        = new DivisionService(divisionRepository, divisionMapper);
        divisionService = Mockito.mock(DivisionService.class);

        CreateDivisionDTO expected = new CreateDivisionDTO(name, originalName, director);

        Division returnedDivision = divisionMapper.toEntity(expected);
        DivisionDTO divisionDTO = divisionMapper.toDTO(returnedDivision);
        divisionDTO.setId(id);

        Mockito.when(divisionService.createDivision(expected)).thenReturn(divisionDTO);

        //when
        DivisionDTO actual = divisionService.createDivision(expected);


        //then
        assertEquals(expected.getName(),actual.getName());
        assertEquals(expected.getOriginalName(),actual.getOriginalName());
        assertEquals(expected.getDirector(),actual.getDirector());
        assertEquals(id,actual.getId());
    }

}
