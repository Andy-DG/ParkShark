package com.controlaltinsert.parkshark.employee.service;

import com.controlaltinsert.parkshark.division.domain.DivisionRepository;
import com.controlaltinsert.parkshark.division.service.DivisionMapper;
import com.controlaltinsert.parkshark.division.service.DivisionService;
import com.controlaltinsert.parkshark.employee.api.EmployeeDTO;
import com.controlaltinsert.parkshark.employee.domain.Employee;
import com.controlaltinsert.parkshark.employee.domain.EmployeeRepository;
import com.controlaltinsert.parkshark.support.address.domain.Address;
import com.controlaltinsert.parkshark.support.address.service.AddressMapper;
import com.controlaltinsert.parkshark.support.postalcode.domain.PostalCode;
import com.controlaltinsert.parkshark.support.postalcode.service.PostalCodeMapper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
@FieldDefaults(level = AccessLevel.PRIVATE)
class EmployeeServiceTest {
    Address address = new Address("Hoheloon", 7, new PostalCode("B4400", "Zulu"));
    String phoneNumber = "+1850829000";
    String mobilePhoneNumber = "+2812400451";
    String email = "business.boy@parkshark.shark";
    PostalCodeMapper postalCodeMapper;
    AddressMapper addressMapper;
    EmployeeMapper employeeMapper;
    EmployeeRepository employeeRepository;
    EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        postalCodeMapper = new PostalCodeMapper();
        addressMapper = new AddressMapper(postalCodeMapper);

        employeeMapper = new EmployeeMapper(addressMapper);
        employeeRepository = Mockito.mock(EmployeeRepository.class);
        employeeService = new EmployeeService(employeeMapper, employeeRepository);
    }

    @Test
    @DisplayName("given an employee when getting employee by id then employee is returned")
    void givenAnEmployeeWhenGettingEmployeeByIdThenEmployeeIsReturned() {
        //given
        Employee employee = new Employee(
                "Business", "Boy",
                address,
                phoneNumber,mobilePhoneNumber,
                email);
        //when

        EmployeeDTO actual = employeeService.getEmployeeById(employee.getId());
        //then
        EmployeeDTO expected = employeeMapper.toDTO(employee);
        assertEquals(expected,actual);

    }

    @Test
    @DisplayName("given a null or blank employee ID when trying to find the employee based on empty id then an exception is thrown")
    void givenANullOrBlankEmployeeIdWhenTryingToFindTheEmployeeBasedOnEmptyIdThenAnExceptionIsThrown() {
        fail("Not implemented");
        //given

        //when

        //then

    }
}
