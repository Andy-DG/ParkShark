package com.controlaltinsert.parkshark.employee.domain;

import com.controlaltinsert.parkshark.support.address.domain.Address;
import com.controlaltinsert.parkshark.support.postalcode.domain.PostalCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    private int id = 666;
    private String firstName = "Harald";
    private String lastName = "The Red";
    Address address = new Address("Stockholmstreet", 12, new PostalCode("111 22", "Stockholm"));
    String phoneNumber = "+46850829000";
    String mobilePhoneNumber = "+46812400451";
    String email = "harald.thered@stockholm.sw";

    String badFormatString = "badFormat";

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
        assertThrows(IllegalArgumentException.class,
                () -> new Employee(firstName, lastName, address, phoneNumber, mobilePhoneNumber, email),
                "Phone-number cannot be empty");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("given an employee with empty or null email fields , IA exception is thrown")
    void givenAnEmployeeWithEmptyOrNullemail_whenCreatingEmployee_thenIllegalArgumentExceptionIsThrown(String nullAndEmpty) {

        //given
        email = nullAndEmpty;
        //when

        //then
        assertThrows(IllegalArgumentException.class,
                () -> new Employee(firstName, lastName, address, phoneNumber, mobilePhoneNumber, email),
                "Email cannot be empty");
    }

    @Test
    @DisplayName("given a bad phone number format and no cellphone when creating a employee then an exception is thrown")
    void givenABadPhoneNumberFormatAndNoCellphoneWhenCreatingAEmployeeThenAnExceptionIsThrown() {

        //given
        phoneNumber = badFormatString;
        mobilePhoneNumber = " ";
        //when

        //then
        assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, address, phoneNumber, mobilePhoneNumber, email), "Invalid phone-number format");
    }

    @Test
    @DisplayName("given a bad mobile phone number format and no phone number when creating a employee then an exception is thrown")
    void givenABadMobilePhoneNumberFormatAndNoPhoneNumberWhenCreatingAEmployeeThenAnExceptionIsThrown() {

        //given
        phoneNumber = "";
        mobilePhoneNumber = badFormatString;
        //when

        //then
        assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, address, phoneNumber, mobilePhoneNumber, email), "Invalid phone-number format");
    }

    @Test
    @DisplayName("given a bad email format when creating a employee then an exception is thrown")
    void givenABadEmailFormatWhenCreatingAEmployeeThenAnExceptionIsThrown() {

        //given
        email = badFormatString;
        //when

        //then
        assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, address, phoneNumber, mobilePhoneNumber, email), "Invalid email format");
    }

//    @Test
//    @DisplayName("given an employee when getting id then id is geturned")
//    void givenAnEmployeeWhenGettingIdThenIdIsGeturned() {
//
// //given
// Employee employee = new Employee(firstName,lastName,address,phoneNumber,mobilePhoneNumber,email);
// //when
// int employeeId = employee.getId();
// //then
//        assertNotEquals(0, employeeId);
//    }
//
}
