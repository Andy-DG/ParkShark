package com.controlaltinsert.parkshark.parkinglot.service;

import com.controlaltinsert.parkshark.division.api.dto.DivisionDTO;
import com.controlaltinsert.parkshark.division.domain.Division;
import com.controlaltinsert.parkshark.division.domain.DivisionRepository;
import com.controlaltinsert.parkshark.division.service.DivisionMapper;
import com.controlaltinsert.parkshark.division.service.DivisionService;
import com.controlaltinsert.parkshark.employee.api.EmployeeDTO;
import com.controlaltinsert.parkshark.employee.domain.Employee;
import com.controlaltinsert.parkshark.employee.domain.EmployeeRepository;
import com.controlaltinsert.parkshark.employee.service.EmployeeMapper;
import com.controlaltinsert.parkshark.employee.service.EmployeeService;
import com.controlaltinsert.parkshark.parkinglot.api.dto.CreateParkingLotDTO;
import com.controlaltinsert.parkshark.parkinglot.api.dto.ParkingLotDTO;
import com.controlaltinsert.parkshark.parkinglot.domain.Category;
import com.controlaltinsert.parkshark.parkinglot.domain.ParkingLot;
import com.controlaltinsert.parkshark.parkinglot.domain.ParkingLotRepository;
import com.controlaltinsert.parkshark.support.address.api.CreateAddressDTO;
import com.controlaltinsert.parkshark.support.address.domain.Address;
import com.controlaltinsert.parkshark.support.address.service.AddressMapper;
import com.controlaltinsert.parkshark.support.postalcode.api.PostalCodeDTO;
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
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class ParkingLotServiceUnitTest {
    private static int id = 1111111;
    int contactPersonId;
    private final Employee contactperson = new Employee("Andy", "Builder",
            new Address("Haaistraat", 6, new PostalCode("8000", "Bruhhe")),
            "+32123456789", "", "andy@parkshark.be");
    private final String name = "ParkShark Hasselt";
    private final String originalName = "Parking Hasselt Station";
    private final Employee director = new Employee("Boris", "De Beer",
            new Address("Nieuwstraat", 1, new PostalCode("1000", "Brussel")),
            "", "+32123456789", "boris.debeer@parkshark.be");
    private final Division division = new Division(name, originalName, director, 0);

    private PostalCodeMapper postalCodeMapper;
    private AddressMapper addressMapper;

    private ParkingLotMapper parkingLotMapper;
    private EmployeeMapper employeeMapper;
    private EmployeeRepository employeeRepository;
    private EmployeeService employeeService;

    private DivisionMapper divisionMapper;
    private DivisionRepository divisionRepository;
    private DivisionService divisionService;


    private ParkingLotRepository parkingLotRepository;
    private ParkingLotService parkingLotService;
    private EmployeeDTO contactPersonDTO;

    DivisionDTO divisionDTO;
    int divisionId;

    @BeforeEach
    void setUp() {
        postalCodeMapper = new PostalCodeMapper();
        addressMapper = new AddressMapper(postalCodeMapper);

        employeeMapper = new EmployeeMapper(addressMapper);
        employeeRepository = Mockito.mock(EmployeeRepository.class);
        employeeService = new EmployeeService(employeeMapper, employeeRepository);

        divisionMapper = new DivisionMapper(employeeMapper);
        divisionRepository = Mockito.mock(DivisionRepository.class);
        divisionService = new DivisionService(divisionRepository, divisionMapper, employeeService);

        parkingLotMapper = new ParkingLotMapper(employeeMapper, divisionMapper);
        parkingLotRepository = Mockito.mock(ParkingLotRepository.class);

        parkingLotService = new ParkingLotService(parkingLotMapper, parkingLotRepository, employeeService, divisionService);

        PostalCodeDTO postalCodeDTO = new PostalCodeDTO("E1000", "Edingburg");
        CreateAddressDTO createAddressDTO = new CreateAddressDTO("RockLane", 15, postalCodeDTO);

        contactPersonId = contactperson.getId();
        divisionId = division.getId();

        contactPersonDTO = new EmployeeDTO(contactPersonId, "Hugh", "Mungus",
                createAddressDTO,
                "+3214778090", "+40478889945", "hugh@mungus.be");
        divisionDTO = divisionMapper.toDTO(division);

        Mockito.when(employeeRepository.findById(contactPersonId)).thenReturn(Optional.of(contactperson));
        Mockito.when(divisionRepository.findById(divisionId)).thenReturn(Optional.of(division));

    }

    @Test
    @DisplayName("given a createParkingDTO with all fields filled, when adding a parking lot, then return the parkinglot")
    void givenACreateParkingDtoWithAllFieldsFilledWhenAddingAParkingLotThenReturnTheParkinglot() {
        CreateParkingLotDTO createParkingLotDTO = new CreateParkingLotDTO(
                "TestParking",
                Category.UNDERGROUND,
                150,
                4.20,
                contactPersonId,
                division.getId());

        ParkingLot parkingLot = parkingLotMapper.toEntity(createParkingLotDTO, contactPersonDTO, divisionDTO);
        ParkingLotDTO lotDTO = parkingLotMapper.toDTO(parkingLot);
        lotDTO.setId(id);
        Mockito.when(parkingLotRepository.save(Mockito.any(ParkingLot.class))).thenReturn(parkingLot);

        //actual test
        ParkingLotDTO actual = parkingLotService.createParkingLot(createParkingLotDTO);

        assertEquals(createParkingLotDTO.getName(), actual.getName());
        assertEquals(createParkingLotDTO.getMaxCapacity(), actual.getMaxCapacity());
        assertEquals(createParkingLotDTO.getCategory(), actual.getCategory());
        assertEquals(createParkingLotDTO.getPricePerHour(), actual.getPricePerHour());
        assertEquals(createParkingLotDTO.getContactPersonId(), actual.getContactPersonDTO().getId());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("given a createParkingLotDTO with a blank name when creating a new parking lot then throw illegalArgumentException")
    void givenACreateParkingLotDtoWithABlankNameWhenCreatingANewParkingLotThenThrowIllegalArgumentException(String name) {
//
        CreateParkingLotDTO createParkingLotDTO = new CreateParkingLotDTO(
                name,
                Category.UNDERGROUND,
                150,
                4.20,
                contactPersonId,
                division.getId());

        assertThrows(IllegalArgumentException.class, () -> this.parkingLotService.createParkingLot(createParkingLotDTO));
    }

    @ParameterizedTest
    @ValueSource(ints = {0,-1,-9999})
    @DisplayName("given a createParkingLotDTO with a maxCapacity of 0 or less when creating a new parking lot then throw illegalArgumentException")
    void givenACreateParkingLotDtoWithAMaxCapacityLessThanZeroWhenCreatingANewParkingLotThenThrowIllegalArgumentException(int maxCapacity) {
        CreateParkingLotDTO createParkingLotDTO = CreateParkingLotDTO.builder()
                .name("Just ja")
                .maxCapacity(maxCapacity)
                .pricePerHour(4.20)
                .category(Category.UNDERGROUND)
                .contactPersonId(contactPersonId)
                .divisionId(divisionId)
                .build();

        assertThrows(IllegalArgumentException.class, () -> this.parkingLotService.createParkingLot(createParkingLotDTO), "Category cannot be null");
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, -1, -0.0001, -500})
    @DisplayName("given a CreateParkingLotDTO with price per hour of zero or less then throw IllegalArgumentException")
    void givenACreateParkingLotDtoWithPricePerHourOfZeroOrLessThenThrowIllegalArgumentException(double price) {
        CreateParkingLotDTO createParkingLotDTO = CreateParkingLotDTO.builder()
                .name("Just ja")
                .maxCapacity(10)
                .pricePerHour(price)
                .category(Category.UNDERGROUND)
                .contactPersonId(contactPersonId)
                .divisionId(divisionId)
                .build();

        assertThrows(IllegalArgumentException.class, () -> this.parkingLotService.createParkingLot(createParkingLotDTO));
    }

    @Test
    @DisplayName("given a CreateParkingDTO with no category when creating a parkinglot, then throw IllegalArgumentException")
    void givenACreateParkingDtoWithNoCategoryWhenCreatingAParkinglotThenThrowIllegalArgumentException() {
        CreateParkingLotDTO createParkingLotDTO = CreateParkingLotDTO.builder()
                .name("Just ja")
                .maxCapacity(0)
                .pricePerHour(4.20)
                .category(null)
                .contactPersonId(contactPersonId)
                .divisionId(divisionId)
                .build();

        assertThrows(IllegalArgumentException.class, () -> this.parkingLotService.createParkingLot(createParkingLotDTO));

    }


}
