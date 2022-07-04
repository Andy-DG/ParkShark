package com.controlaltinsert.parkshark.employee.service;

import com.controlaltinsert.parkshark.employee.api.EmployeeDTO;
import com.controlaltinsert.parkshark.employee.domain.Employee;
import com.controlaltinsert.parkshark.employee.domain.EmployeeRepository;
import com.controlaltinsert.parkshark.support.address.api.CreateAddressDTO;
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

import java.util.Optional;

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
                mobilePhoneNumber, phoneNumber,
                email);
        CreateAddressDTO createAddressDTO = addressMapper.toDTO(address);
        //when
        Mockito.when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));
        EmployeeDTO actual = employeeService.getEmployeeById(employee.getId());
        //then

        EmployeeDTO expected = new EmployeeDTO(employee.getId(),
                "Business", "Boy",
                createAddressDTO,
                phoneNumber,mobilePhoneNumber,
                email);
        assertEquals(expected,actual);

    }
}
