package com.controlaltinsert.parkshark.parkinglot.api;

import com.controlaltinsert.parkshark.division.domain.Division;
import com.controlaltinsert.parkshark.employee.domain.Employee;
import com.controlaltinsert.parkshark.parkinglot.api.dto.ParkingLotDTO;
import com.controlaltinsert.parkshark.parkinglot.domain.Category;
import com.controlaltinsert.parkshark.parkinglot.domain.ParkingLot;
import com.controlaltinsert.parkshark.parkinglot.domain.ParkingLotRepository;
import com.controlaltinsert.parkshark.parkinglot.service.ParkingLotMapper;
import com.controlaltinsert.parkshark.parkinglot.service.ParkingLotService;
import com.controlaltinsert.parkshark.support.address.domain.Address;
import com.controlaltinsert.parkshark.support.postalcode.domain.PostalCode;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.util.Lists.newArrayList;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ParkingLotControllerIntegrationTest {
    @LocalServerPort
    private int port;

    private ParkingLotRepository parkingLotRepository;

    private ParkingLotMapper parkingLotMapper;

    private ParkingLotService parkingLotService;

    @Test
    void givenParkingLot_whenParkingLotIsCreated_returnParkingLot() {
        //Given
        int id = 666;
        String name = "ParkShark Hasselt";
        String originalName = "Parking Hasselt Station";
        Employee director = new Employee("Boris", "De Beer",
                new Address("Nieuwstraat", 1, new PostalCode("1000", "Brussel")),
                "", "+32123456789", "boris.debeer@parkshark.be");

        ParkingLot parkingLot =
                new ParkingLot(
                        "SharkParkLot",
                        Category.ABOVE_GROUND,
                        10,
                        10,
                        new Employee(
                                "Andy",
                                "De Gheldere",
                                new Address("Daverlostraat", 61, new PostalCode("8000", "Brugge")),
                                "636 856 789",
                                "636 856 789",
                                "sharky@mail.com"),
                        new Division(name, originalName, director, 0));


        //When
        RestAssured
                .given()
                .port(port)
                .body(parkingLot)
                .contentType(JSON)
                .when()
                .accept(JSON)
                .post("/parkinglots")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());

        //Then
        Assertions.assertThat(parkingLotRepository.findAll()).extracting(ParkingLot::getName).contains("name");
    }

    @Test
    @Sql("classpath:add_parking_lot.sql")
    void getAllParkingLots() {
        List<ParkingLotDTO> actualparkingLots = newArrayList(given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .contentType(JSON)
                .get("/parkinglots")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract().as(ParkingLotDTO[].class));

        Assertions.assertThat(actualparkingLots).extracting(ParkingLotDTO::getName).contains("SharkLot");
        Assertions.assertThat(actualparkingLots).extracting(parkingLotDTO -> parkingLotDTO.getContactPersonDTO().getEmail()).contains("andy@shark.com");
    }
}