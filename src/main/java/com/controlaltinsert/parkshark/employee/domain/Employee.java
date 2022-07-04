package com.controlaltinsert.parkshark.employee.domain;


import com.controlaltinsert.parkshark.support.address.domain.Address;
import com.controlaltinsert.parkshark.util.Validate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "EMPLOYEE", schema = "parkshark")
@NoArgsConstructor
@Getter
public class Employee {

    @Transient
    private final Logger employeeLogger = LoggerFactory.getLogger(Employee.class);

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPLOYEE_id_seq")
    @SequenceGenerator(name = "EMPLOYEE_id_seq", sequenceName = "EMPLOYEE_id_seq", allocationSize = 1)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_address_id")
    private Address address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "mobile_phone_number")
    private String mobilePhoneNumber;

    @Column(name = "email")
    private String email;

    public Employee(String firstName, String lastName, Address address, String phoneNumber, String mobilePhoneNumber, String email) {
        Validate.validateToHaveAPhoneNumber(phoneNumber, mobilePhoneNumber);
        this.firstName = Validate.validateString(firstName, "Firstname");
        this.lastName = Validate.validateString(lastName, "Lastname");
        this.address = Validate.validateAddress(address);
        this.phoneNumber = phoneNumber;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.email = Validate.validateEmail(email);
        employeeLogger.info("Created an employee successfully");
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = Validate.validateString(firstName, "Firstname");
    }

    public void setLastName(String lastName) {
        this.lastName = Validate.validateString(lastName, "Lastname");
    }

    public void setAddress(Address address) {
        this.address = Validate.validateAddress(address);
    }

    public void setPhoneNumber(String phoneNumber) {
        Validate.validateToHaveAPhoneNumber(phoneNumber, this.mobilePhoneNumber);
        this.phoneNumber = Validate.validatePhoneNumberFormat(phoneNumber);
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        Validate.validateToHaveAPhoneNumber(this.phoneNumber, mobilePhoneNumber);
        this.mobilePhoneNumber = Validate.validatePhoneNumberFormat(mobilePhoneNumber);
    }

    public void setEmail(String email) {
        this.email = Validate.validateEmail(email);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return id == employee.id && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName) && Objects.equals(phoneNumber, employee.phoneNumber) && Objects.equals(mobilePhoneNumber, employee.mobilePhoneNumber) && Objects.equals(email, employee.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, phoneNumber, mobilePhoneNumber, email);
    }
}
