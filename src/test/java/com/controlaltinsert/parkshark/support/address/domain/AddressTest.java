package com.controlaltinsert.parkshark.support.address.domain;

import com.controlaltinsert.parkshark.employee.domain.Employee;
import com.controlaltinsert.parkshark.support.postalcode.domain.PostalCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {
    private String streetName = "Stockholmstreet";
    private int streetNumber = 12;
    private PostalCode postalCode = new PostalCode("111 22", "Stockholm");

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("given an address with empty or null streetname , IllegalArgument exception is thrown")
    void givenAnAddressWithEmptyOrNullcellStreetName_whenCreatingAddress_thenIllegalArgumentExceptionIsThrown(String nullAndEmpty) {

        //given
        streetName = nullAndEmpty;
        //when

        //then
        assertThrows(IllegalArgumentException.class, () -> new Address(streetName,streetNumber,postalCode));
    }

}
