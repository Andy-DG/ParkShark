package com.controlaltinsert.parkshark.division.service;

import com.controlaltinsert.parkshark.division.api.dto.CreateDivisionDTO;
import com.controlaltinsert.parkshark.division.api.dto.DivisionDTO;
import com.controlaltinsert.parkshark.division.domain.Division;
import com.controlaltinsert.parkshark.division.domain.DivisionRepository;
import com.controlaltinsert.parkshark.employee.api.EmployeeDTO;
import com.controlaltinsert.parkshark.employee.domain.Employee;
import com.controlaltinsert.parkshark.employee.domain.EmployeeRepository;
import com.controlaltinsert.parkshark.employee.service.EmployeeMapper;
import com.controlaltinsert.parkshark.employee.service.EmployeeService;
import com.controlaltinsert.parkshark.support.address.domain.Address;
import com.controlaltinsert.parkshark.support.address.service.AddressMapper;
import com.controlaltinsert.parkshark.support.postalcode.domain.PostalCode;
import com.controlaltinsert.parkshark.support.postalcode.service.PostalCodeMapper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
@ExtendWith(MockitoExtension.class)
class DivisionServiceTest {
    final int id = 1;

    String name = "ParkShark Ghelamco";
    String originalName = "Parking Service Buffalo K.A.A. Gent Ghelamco";
    Address address = new Address("Hoheloon", 7, new PostalCode("B4400", "Zulu"));
    Employee director = new Employee("Rick", "Sanchez", address, "+3214804060", "+32479252939", "man@domain.com");

    PostalCodeMapper postalCodeMapper;
    AddressMapper addressMapper;
    EmployeeMapper employeeMapper;
    EmployeeRepository employeeRepository;
    EmployeeService employeeService;
    DivisionMapper divisionMapper;
    DivisionService divisionService;
    DivisionRepository divisionRepository;

    @BeforeEach
    void setUp() {
        postalCodeMapper = new PostalCodeMapper();
        addressMapper = new AddressMapper(postalCodeMapper);
        employeeMapper = new EmployeeMapper(addressMapper);
        divisionMapper = new DivisionMapper(employeeMapper);

        employeeRepository = Mockito.mock(EmployeeRepository.class);
        employeeService = new EmployeeService(employeeMapper, employeeRepository);

        divisionRepository = Mockito.mock(DivisionRepository.class);
        divisionService = new DivisionService(divisionRepository, divisionMapper, employeeService);
    }

    @Test
    @DisplayName("given a division service, when creating a new division, the division exists")
    void givenADivisionServiceWhenCreatingANewDivisionTheDivisionExists() {
        //given
        divisionService = Mockito.mock(DivisionService.class);
        EmployeeDTO directorDTO = employeeMapper.toDTO(director);
        CreateDivisionDTO expectedDTO = new CreateDivisionDTO(name, originalName, directorDTO.getId(), 0);
        Division expected = divisionMapper.toEntity(expectedDTO, directorDTO);
        DivisionDTO divisionDTO = divisionMapper.toDTO(expected);
        divisionDTO.setId(id);
        Mockito.when(divisionService.createDivision(expectedDTO)).thenReturn(divisionDTO);
        //when
        DivisionDTO actual = divisionService.createDivision(expectedDTO);
        //then
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getOriginalName(), actual.getOriginalName());
        assertEquals(expected.getDirector().getId(), actual.getDirectorId());
        assertEquals(id, actual.getId());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("given a division to create with an empty or null name when creating new division then throw illegal argument exception")
    void givenADivisionToCreateWithAnEmptyOrNullNameWhenCreatingNewDivisionThenThrowIllegalArgumentException(String nullAndEmpty) {
        //given
        name = nullAndEmpty;
        CreateDivisionDTO createDivisionDTO = new CreateDivisionDTO(name, originalName, director.getId());


        //when

        //then
        assertThrows(IllegalArgumentException.class, () -> divisionService.createDivision(createDivisionDTO));
    }

    @Test
    @DisplayName("when creating a new division without specifying the head division, the head division id is zero")
    void whenCreatingANewDivisionWithoutSpecifyingTheHeadDivisionTheHeadDivisionIdIsZero() {

        //given

        //when
        Division division = new Division(name,originalName,director);

        //then
        assertEquals(0,division.getFkHeadDivisionId());

    }

    @Test
    @DisplayName("given a division and a subdivision, when creating a new subdivision of that subdivision then an exception is thrown")
    void givenADivisionAndASubdivisionWhenCreatingANewSubdivisionOfThatSubdivisionThenAnExceptionIsThrown() {
        //given
        Division headDivision = new Division(name,originalName,director);
        Division subDivision = new Division("subdivision","original name", director, headDivision.getId());
        //when
        CreateDivisionDTO subSubDivision = new CreateDivisionDTO("subSub","original",director.getId(),subDivision.getId());
        //then
        assertThrows(IllegalArgumentException.class, () -> divisionService.createDivision(subSubDivision));

    }
}
