package com.controlaltinsert.parkshark.parkinglot.service;

import com.controlaltinsert.parkshark.employee.Employee;
import com.controlaltinsert.parkshark.parkinglot.api.dto.CreateParkingLotDTO;
import com.controlaltinsert.parkshark.parkinglot.api.dto.ParkingLotDTO;
import com.controlaltinsert.parkshark.parkinglot.domain.Category;
import com.controlaltinsert.parkshark.parkinglot.domain.ParkingLot;
import com.controlaltinsert.parkshark.parkinglot.domain.ParkingLotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class ParkingLotServiceUnitTest {
    private static int id = 1111111;


    private ParkingLotMapper parkingLotMapper;

    private ParkingLotRepository parkingLotRepository;

    private ParkingLotService parkingLotService;



    @BeforeEach
    void setUp(){
        parkingLotMapper = new ParkingLotMapper();
        parkingLotRepository = Mockito.mock(ParkingLotRepository.class);
        parkingLotService = new ParkingLotService(parkingLotMapper, parkingLotRepository);
    }

    @Test
    @DisplayName("given a createParkingDTO with all fields filled, when adding a parking lot, then return the parkinglot")
    void givenACreateParkingDtoWithAllFieldsFilledWhenAddingAParkingLotThenReturnTheParkinglot() {
        CreateParkingLotDTO createParkingLotDTO = CreateParkingLotDTO.builder()
                .name("TestParking")
                .maxCapacity(150)
                .pricePerHour(4.20)
                .category(Category.UNDERGROUND)
                .contactPerson(new Employee())
                .build();

        ParkingLot returnedParkingLot = this.parkingLotMapper.toEntity(createParkingLotDTO);
        ParkingLotDTO lotDTO = this.parkingLotMapper.toDTO(returnedParkingLot);
        lotDTO.setId(id);
        System.out.println("1 " + lotDTO.getId());

        Mockito.when(this.parkingLotService.createParkingLot(createParkingLotDTO)).thenReturn(lotDTO);

        //actual test
        ParkingLotDTO actual = this.parkingLotService.createParkingLot(createParkingLotDTO);
        System.out.println("2 " + actual.getId());

        assertEquals(createParkingLotDTO.getName(), actual.getName());
        assertEquals(createParkingLotDTO.getMaxCapacity(), actual.getMaxCapacity());
        assertEquals(createParkingLotDTO.getCategory(), actual.getCategory());
        assertEquals(createParkingLotDTO.getPricePerHour(), actual.getPricePerHour());
        assertEquals(createParkingLotDTO.getContactPerson(), actual.getContactPerson());
        
        assertEquals(id, actual.getId());
    }

    @Test
    @DisplayName("given a createParkingLotDTO with a blank name when creating a new parking lot then throw illegalArgumentException")
    void givenACreateParkingLotDtoWithABlankNameWhenCreatingANewParkingLotThenThrowIllegalArgumentException() {
        CreateParkingLotDTO createParkingLotDTO = CreateParkingLotDTO.builder()
                .name(" ")
                .maxCapacity(150)
                .pricePerHour(4.20)
                .category(Category.UNDERGROUND)
                .contactPerson(new Employee()) //<- TODO
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
                .contactPerson(new Employee()) //<- TODO
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
                .contactPerson(new Employee()) //<- TODO
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
                .contactPerson(new Employee()) //<- TODO
                .build();

        assertThrows(IllegalArgumentException.class, () -> this.parkingLotService.createParkingLot(createParkingLotDTO));

    }

    @Test
    @DisplayName("given a CreateParkingDTO with no ContactPerson then throw illegalArgumentException")
    void givenACreateParkingDtoWithNoContactPersonThenThrowIllegalArgumentException() {
        CreateParkingLotDTO createParkingLotDTO = CreateParkingLotDTO.builder()
                .name("Just ja")
                .maxCapacity(16515)
                .pricePerHour(4.20)
                .category(Category.UNDERGROUND)
                .contactPerson(null)
                .build();

        assertThrows(IllegalArgumentException.class, () -> this.parkingLotService.createParkingLot(createParkingLotDTO));
    }


}