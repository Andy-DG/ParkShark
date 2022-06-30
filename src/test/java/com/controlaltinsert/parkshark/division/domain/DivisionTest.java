package com.controlaltinsert.parkshark.division.domain;

import com.controlaltinsert.parkshark.employee.domain.Employee;
import com.controlaltinsert.parkshark.support.address.domain.Address;
import com.controlaltinsert.parkshark.support.postalcode.domain.PostalCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;

class DivisionTest {
    private final  int id = 666;
    private final String name = "ParkShark Hasselt";
    private final String originalName = "Parking Hasselt Station";
    private final Employee director = new Employee("Boris", "De Beer",
            new Address("Nieuwstraat", 1, new PostalCode("1000", "Brussel")),
            "", "+32123456789", "boris.debeer@parkshark.be");

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("given a null or balnk name, with ok original name and director when creating a director then throw exception")
    void givenANullOrBlankNamewithOKOriginalNameAndDrcetorWhenCreatingADirectorThenThrowException(String nullAndEmpty) {

               //then
        assertThrows(IllegalArgumentException.class, () -> new Division(nullAndEmpty, originalName, director,0));
    }

}
