package com.controlaltinsert.parkshark.parkinglot.service;

import com.controlaltinsert.parkshark.employee.api.EmployeeDTO;
import com.controlaltinsert.parkshark.employee.domain.Employee;
import com.controlaltinsert.parkshark.employee.service.EmployeeMapper;
import com.controlaltinsert.parkshark.parkinglot.api.dto.CreateParkingLotDTO;
import com.controlaltinsert.parkshark.parkinglot.api.dto.ParkingLotDTO;
import com.controlaltinsert.parkshark.parkinglot.domain.Category;
import com.controlaltinsert.parkshark.parkinglot.domain.ParkingLot;
import com.controlaltinsert.parkshark.parkinglot.domain.ParkingLotRepository;
import com.controlaltinsert.parkshark.support.address.api.AddressDTO;
import com.controlaltinsert.parkshark.support.address.domain.AddressRepository;
import com.controlaltinsert.parkshark.support.address.service.AddressMapper;
import com.controlaltinsert.parkshark.support.postalcode.api.PostalCodeDTO;
import com.controlaltinsert.parkshark.support.postalcode.service.PostalCodeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class ParkingLotServiceUnitTest {
    private static int id = 1111111;


    private ParkingLotMapper parkingLotMapper;
    private EmployeeMapper employeeMapper;
    private PostalCodeMapper postalCodeMapper;
    private AddressMapper addressMapper;
    private ParkingLotRepository parkingLotRepository;
    private ParkingLotService parkingLotService;
    private EmployeeDTO employeeDTO;


    @BeforeEach
    void setUp(){
        postalCodeMapper = new PostalCodeMapper();
        addressMapper = new AddressMapper(postalCodeMapper);
        employeeMapper = new EmployeeMapper(addressMapper);
        parkingLotMapper = new ParkingLotMapper(employeeMapper);
        parkingLotRepository = Mockito.mock(ParkingLotRepository.class);
        parkingLotService = new ParkingLotService(parkingLotMapper, parkingLotRepository);

        PostalCodeDTO postalCodeDTO = new PostalCodeDTO(1000, "E1000", "Edingburg");
        AddressDTO addressDTO = new AddressDTO(40, "RockLane", 15, postalCodeDTO);
        employeeDTO = new EmployeeDTO(15, "Hugh", "Mungus", addressDTO, "+3214778090", "+40478889945", "hugh@mungus.be");
    }

    @Test
    @DisplayName("given a createParkingDTO with all fields filled, when adding a parking lot, then return the parkinglot")
    void givenACreateParkingDtoWithAllFieldsFilledWhenAddingAParkingLotThenReturnTheParkinglot() {
        CreateParkingLotDTO createParkingLotDTO = CreateParkingLotDTO.builder()
                .name("TestParking")
                .maxCapacity(150)
                .pricePerHour(4.20)
                .category(Category.UNDERGROUND)
                .contactPerson(employeeDTO)
                .build();

        ParkingLot returnedParkingLot = this.parkingLotMapper.toEntity(createParkingLotDTO);
        ParkingLotDTO lotDTO = this.parkingLotMapper.toDTO(returnedParkingLot);
        lotDTO.setId(id);

        postalCodeMapper = Mockito.mock(PostalCodeMapper.class);
        addressMapper = Mockito.mock(AddressMapper.class);
        addressMapper.setPostalCodeMapper(postalCodeMapper);
        employeeMapper = Mockito.mock(EmployeeMapper.class);
        employeeMapper.setAddressMapper(addressMapper);
        parkingLotMapper = Mockito.mock(ParkingLotMapper.class);
        parkingLotMapper.setEmployeeMapper(employeeMapper);
        parkingLotRepository = Mockito.mock(ParkingLotRepository.class);
        parkingLotService = Mockito.mock(ParkingLotService.class);
        parkingLotService.setParkingLotRepository(parkingLotRepository);
        parkingLotService.setParkingLotMapper(parkingLotMapper);

        //Mockito.when(this.employeeMapper.toDTO(returnedParkingLot.getContactPerson())).thenReturn(employeeDTO);
        Mockito.when(this.parkingLotService.createParkingLot(createParkingLotDTO)).thenReturn(lotDTO);


        //actual test
        ParkingLotDTO actual = this.parkingLotService.createParkingLot(createParkingLotDTO);

        assertEquals(createParkingLotDTO.getName(), actual.getName());
        assertEquals(createParkingLotDTO.getMaxCapacity(), actual.getMaxCapacity());
        assertEquals(createParkingLotDTO.getCategory(), actual.getCategory());
        assertEquals(createParkingLotDTO.getPricePerHour(), actual.getPricePerHour());
        //assertEquals(createParkingLotDTO.getContactPerson(), actual.getContactPerson());

        assertEquals(id, actual.getId());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("given a createParkingLotDTO with a blank name when creating a new parking lot then throw illegalArgumentException")
    void givenACreateParkingLotDtoWithABlankNameWhenCreatingANewParkingLotThenThrowIllegalArgumentException(String nullAndEmpty) {
        CreateParkingLotDTO createParkingLotDTO = CreateParkingLotDTO.builder()
                .name(nullAndEmpty)
                .maxCapacity(150)
                .pricePerHour(4.20)
                .category(Category.UNDERGROUND)
                .contactPerson(employeeDTO) //<- TODO
                .build();

        assertThrows(IllegalArgumentException.class, () -> this.parkingLotService.createParkingLot(createParkingLotDTO));
    }

    @Test
    @DisplayName("given a createParkingLotDTO with a maxCapacity less than 0 when creating a new parking lot then throw illegalArgumentException")
    void givenACreateParkingLotDtoWithAMaxCapacityLessThanZeroWhenCreatingANewParkingLotThenThrowIllegalArgumentException() {
        CreateParkingLotDTO createParkingLotDTO = CreateParkingLotDTO.builder()
                .name("Just ja")
                .maxCapacity(-1)
                .pricePerHour(4.20)
                .category(Category.UNDERGROUND)
                .contactPerson(employeeDTO) //<- TODO
                .build();

        assertThrows(IllegalArgumentException.class, () -> this.parkingLotService.createParkingLot(createParkingLotDTO));
    }

    @Test
    @DisplayName("given a CreateParkingLotDTO with price per hour of zero or less then throw IllegalArgumentException")
    void givenACreateParkingLotDtoWithPricePerHourOfZeroOrLessThenThrowIllegalArgumentException() {
        CreateParkingLotDTO createParkingLotDTO = CreateParkingLotDTO.builder()
                .name("Just ja")
                .maxCapacity(10)
                .pricePerHour(0)
                .category(Category.UNDERGROUND)
                .contactPerson(employeeDTO) //<- TODO
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
                .contactPerson(employeeDTO) //<- TODO
                .build();

        assertThrows(IllegalArgumentException.class, () -> this.parkingLotService.createParkingLot(createParkingLotDTO));

    }




}
