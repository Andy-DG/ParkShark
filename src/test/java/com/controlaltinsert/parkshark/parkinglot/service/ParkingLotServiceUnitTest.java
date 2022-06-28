package com.controlaltinsert.parkshark.parkinglot.service;

import com.controlaltinsert.parkshark.parkinglot.api.dto.CreateParkingLotDTO;
import com.controlaltinsert.parkshark.parkinglot.api.dto.ParkingLotDTO;
import com.controlaltinsert.parkshark.parkinglot.domain.Category;
import com.controlaltinsert.parkshark.parkinglot.domain.ParkingLot;
import com.controlaltinsert.parkshark.parkinglot.domain.ParkingLotRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import static org.junit.jupiter.api.Assertions.*;


class ParkingLotServiceUnitTest {
    private static int id = 1111111;

    private ParkingLotMapper parkingLotMapper;

    @Mock
    private ParkingLotRepository parkingLotRepository;

    @InjectMocks
    private ParkingLotService parkingLotService;

    @Test
    @DisplayName("given a createParkingDTO with all fields filled, when adding a parking lot, then return the parkinglot")
    void givenACreateParkingDtoWithAllFieldsFilledWhenAddingAParkingLotThenReturnTheParkinglot() {
        CreateParkingLotDTO createParkingLotDTO = CreateParkingLotDTO.builder()
                .name("TestParking")
                .maxCapacity(150)
                .pricePerHour(4.20)
                .category(Category.UNDERGROUND)
                .contactPerson() //<- TODO
                .build();

        ParkingLot parkingLot = this.parkingLotMapper.toEntity(createParkingLotDTO);

        ParkingLot returnedParkingLot = this.parkingLotMapper.toEntity(createParkingLotDTO);
        returnedParkingLot.setId(id);

        Mockito.when(this.parkingLotRepository.save(parkingLot)).thenReturn(returnedParkingLot);

        //actual test
        ParkingLotDTO actual = this.parkingLotService.createParkingLot(createParkingLotDTO);
        assertEquals(createParkingLotDTO.getName(), actual.getName());
        assertEquals(createParkingLotDTO.getMaxCapacity(), actual.getMaxCapacity());
        assertEquals(createParkingLotDTO.getCategory(), actual.getCategory());
        assertEquals(createParkingLotDTO.getPricePerHour(), actual.getPricePerHour());
        assertEquals(createParkingLotDTO.getContactPerson(), actual.getContactPerson());
        
        assertEquals(id, actual.getId());
    }

    @Test
    @DisplayName("given a createParkingLotDTO with a blankname when creating a new parking lot then throw illegalArgumentException")
    void givenACreateParkingLotDtoWithABlanknameWhenCreatingANewParkingLotThenThrowIllegalArgumentException() {
        CreateParkingLotDTO createParkingLotDTO = CreateParkingLotDTO.builder()
                .name(" ")
                .maxCapacity(150)
                .pricePerHour(4.20)
                .category(Category.UNDERGROUND)
                .contactPerson() //<- TODO
                .build();

        assertThrows(IllegalArgumentException.class, () -> this.parkingLotService.createParkingLot(createParkingLotDTO));
    }

    @Test
    @DisplayName("given a createParkingLotDTO with a maxCapacity less than 0 when creating a new parking lot then throw illegalArgumentException")
    void givenACreateParkingLotDtoWithAMaxCapacityLessThanZeroWhenCreatingANewParkingLotThenThrowIllegalArgumentException() {
        CreateParkingLotDTO createParkingLotDTO = CreateParkingLotDTO.builder()
                .name("Just ja")
                .maxCapacity(0)
                .pricePerHour(4.20)
                .category(Category.UNDERGROUND)
                .contactPerson() //<- TODO
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
                .contactPerson() //<- TODO
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
                .contactPerson() //<- TODO
                .build();

        assertThrows(IllegalArgumentException.class, () -> this.parkingLotService.createParkingLot(createParkingLotDTO));

    }

    @Test
    @DisplayName("given a CreateParkingDTO with no ContactPerson then throw illegalArgumentException")
    void givenACreateParkingDtoWithNoContactPersonThenThrowIllegalArgumentException() {
        CreateParkingLotDTO createParkingLotDTO = CreateParkingLotDTO.builder()
                .name("Just ja")
                .maxCapacity(0)
                .pricePerHour(4.20)
                .category(Category.UNDERGROUND)
                .contactPerson(null)
                .build();

        assertThrows(IllegalArgumentException.class, () -> this.parkingLotService.createParkingLot(createParkingLotDTO));
    }


}