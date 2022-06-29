package com.controlaltinsert.parkshark.division.service;

import com.controlaltinsert.parkshark.division.api.dto.CreateDivisionDTO;
import com.controlaltinsert.parkshark.division.api.dto.DivisionDTO;
import com.controlaltinsert.parkshark.division.domain.Division;
import com.controlaltinsert.parkshark.division.domain.DivisionRepository;
import com.controlaltinsert.parkshark.employee.domain.Employee;
import com.controlaltinsert.parkshark.employee.service.EmployeeMapper;
import com.controlaltinsert.parkshark.support.address.domain.Address;
import com.controlaltinsert.parkshark.support.address.service.AddressMapper;
import com.controlaltinsert.parkshark.support.postalcode.domain.PostalCode;
import com.controlaltinsert.parkshark.support.postalcode.service.PostalCodeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DivisionServiceTest {
    private int id = 11;
    private String name = "ParkShark Ghelamco";
    private String originalName = "Parking Service Buffalo K.A.A. Gent Ghelamco";
    private Address address = new Address("Hoheloon", 7, new PostalCode("B4400", "Zulu"));
    private Employee director = new Employee("Rick", "Sanchez", address, "+3214804060", "+32479252939", "man@domain.com");

    private PostalCodeMapper postalCodeMapper;
    private AddressMapper addressMapper;
    private EmployeeMapper employeeMapper;
    private DivisionMapper divisionMapper;
    private DivisionService divisionService;
    private DivisionRepository divisionRepository;

    @BeforeEach
    void setUp() {
        postalCodeMapper = new PostalCodeMapper();
        addressMapper = new AddressMapper(postalCodeMapper);
        employeeMapper = new EmployeeMapper(addressMapper);
        divisionMapper = new DivisionMapper(employeeMapper);
        divisionRepository = Mockito.mock(DivisionRepository.class);
        divisionService = new DivisionService(divisionRepository, divisionMapper);
    }

    @Test
    @DisplayName("given a division service, when creating a new division, the division exists")
    void givenADivisionServiceWhenCreatingANewDivisionTheDivisionExists() {

        //given
        divisionService = Mockito.mock(DivisionService.class);

        CreateDivisionDTO expected = new CreateDivisionDTO(name, originalName, employeeMapper.toDTO(director));

        Division returnedDivision = divisionMapper.toEntity(expected);
        DivisionDTO divisionDTO = divisionMapper.toDTO(returnedDivision);
        divisionDTO.setId(id);

        Mockito.when(divisionService.createDivision(expected)).thenReturn(divisionDTO);

        //when
        DivisionDTO actual = divisionService.createDivision(expected);


        //then
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getOriginalName(), actual.getOriginalName());
        assertEquals(expected.getDirector(), actual.getDirector());
        assertEquals(id, actual.getId());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("given a division to create with an empty or null name when creating new division then throw illegal argument exception")
    void givenADivisionToCreateWithAnEmptyOrNullNameWhenCreatingNewDivisionThenThrowIllegalArgumentException(String nullAndEmpty) {

 //given
        name = nullAndEmpty;
        CreateDivisionDTO createDivisionDTO = new CreateDivisionDTO(name, originalName, employeeMapper.toDTO(director));


 //when

 //then
        assertThrows(IllegalArgumentException.class, () -> divisionService.createDivision(createDivisionDTO));


    }

}
