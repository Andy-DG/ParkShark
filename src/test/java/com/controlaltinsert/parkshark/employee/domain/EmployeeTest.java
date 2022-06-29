package com.controlaltinsert.parkshark.employee.domain;

import com.controlaltinsert.parkshark.support.address.domain.Address;
import com.controlaltinsert.parkshark.support.postalcode.domain.PostalCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.assertThrows;

class EmployeeTest {
    private int id = 666;
    private String firstName = "Harald";
    private String lastName = "The Red";
    Address address = new Address("Stockholmstreet", 12, new PostalCode("111 22", "Stockholm"));
    String phoneNumber = "+46 8 508 290 00";
    String mobilePhoneNumber = "+46812400451";
    String email = "harald.thered@stockholm.sw";

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("given an employee with empty or null first name fields , IA exception is thrown")
    void givenAnEmployeeWithEmptyOrNullFirstName_whenCreatingEmployee_thenIllegalArgumentExceptionIsThrown(String nullAndEmpty) {

        //given
        firstName = nullAndEmpty;
        //when

        //then
        assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, address, phoneNumber, mobilePhoneNumber, email));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("given an employee with empty or null Last name fields , IA exception is thrown")
    void givenAnEmployeeWithEmptyOrNullLastName_whenCreatingEmployee_thenIllegalArgumentExceptionIsThrown(String nullAndEmpty) {

        //given
        lastName = nullAndEmpty;
        //when

        //then
        assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, address, phoneNumber, mobilePhoneNumber, email));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("given an employee with empty or null phoneNumber and mobilePhoneNumber fields , IllegalArgument exception is thrown")
    void givenAnEmployeeWithEmptyOrNullPhoneNumberAndMobilePhoneNumeber_whenCreatingEmployee_thenIllegalArgumentExceptionIsThrown(String nullAndEmpty) {

        //given
        phoneNumber = nullAndEmpty;
        mobilePhoneNumber = nullAndEmpty;
        //when

        //then
        assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, address, phoneNumber, mobilePhoneNumber, email));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("given an employee with empty or null cellphoneNumber fields , IA exception is thrown")
    void givenAnEmployeeWithEmptyOrNullMobilephoneNumber_whenCreatingEmployee_thenIllegalArgumentExceptionIsThrown(String nullAndEmpty) {

        //given
        mobilePhoneNumber = nullAndEmpty;
        //when

        //then
        assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, address, phoneNumber, mobilePhoneNumber, email));
    }
    // TODO this test should fail!

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("given an employee with empty or null email fields , IA exception is thrown")
    void givenAnEmployeeWithEmptyOrNullemail_whenCreatingEmployee_thenIllegalArgumentExceptionIsThrown(String nullAndEmpty) {

        //given
        email = nullAndEmpty;
        //when

        //then
        assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, address, phoneNumber, mobilePhoneNumber, email));
    }

}
