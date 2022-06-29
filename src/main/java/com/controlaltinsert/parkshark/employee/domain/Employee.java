package com.controlaltinsert.parkshark.employee.domain;


import com.controlaltinsert.parkshark.support.address.domain.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "employee")
@NoArgsConstructor
@Getter
public class Employee {
    public static final String PHONE_VALIDATION =
            "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
                    + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
                    + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";

    public static final String OWASP_EMAIL_VALIDATION = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    @Transient
    private final Logger employeeLogger = LoggerFactory.getLogger(Employee.class);

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
    @SequenceGenerator(name = "employee_seq")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "fk_address_id")
    private Address address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "mobile_phone_number")
    private String mobilePhoneNumber;

    @Column(name = "email")
    private String email;

    public Employee(String firstName, String lastName, Address address, String phoneNumber, String mobilePhoneNumber, String email) {
        validateToHaveAPhoneNumber(phoneNumber, mobilePhoneNumber);
        this.firstName = validateFirstName(firstName);
        this.lastName = validateLastName(lastName);
        this.address = validateAddress(address);
        this.phoneNumber = validatePhoneNumber(phoneNumber);
        this.mobilePhoneNumber = validatePhoneNumber(mobilePhoneNumber);
        this.email = validateEmail(email);
        employeeLogger.info("Created an employee successfully");
    }

    private String validateEmail(String email) {
        if (email == null) {
            String errorMessage = "Email cannot be empty";
            employeeLogger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        Pattern pattern = Pattern.compile(OWASP_EMAIL_VALIDATION);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            String errorMessage = "Invalid email format";
            employeeLogger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        return email;
    }

    //  Phone-number has to be of one of the following formats: 2055550125, 202 555 0125, (202) 555-0125, +111 (202) 555-0125,
    //      636 856 789, +111 636 856 789, 636 85 67 89, +111 636 85 67 89
    private void validateToHaveAPhoneNumber(String phoneNumber, String mobilePhoneNumber) {
        if (phoneNumber == null && mobilePhoneNumber == null) {
            String errorMessage = "You need either a phone-number or a mobile phone-number";
            employeeLogger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private String validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            String errorMessage = "Phone-number cannot be empty";
            employeeLogger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        Pattern pattern = Pattern.compile(PHONE_VALIDATION);
        Matcher matcher = pattern.matcher(phoneNumber);
        if (!matcher.matches()) {
            String errorMessage = "Invalid phone-number format";
            employeeLogger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        return phoneNumber;
    }

    private String validateFirstName(String firstName){
        if(firstName == null || firstName.isBlank()){
            String message = "First name cannot be null";
            employeeLogger.error(message);
            throw new IllegalArgumentException(message);
        }
        return firstName;
    }

    private String validateLastName(String lastName){
        if(lastName == null || lastName.isBlank()){
            String message = "Last name cannot be null";
            employeeLogger.error(message);
            throw new IllegalArgumentException(message);
        }
        return lastName;
    }

    private Address validateAddress(Address address){
        if(address == null){
            String message = "Address cannot be null";
            employeeLogger.error(message);
            throw new IllegalArgumentException(message);
        }
        return address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = validateFirstName(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName = validateLastName(lastName);
    }

    public void setAddress(Address address) {
        this.address = validateAddress(address);
    }

    public void setPhoneNumber(String phoneNumber) {
        validateToHaveAPhoneNumber(phoneNumber, this.mobilePhoneNumber);
        this.phoneNumber = validatePhoneNumber(phoneNumber);
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        validateToHaveAPhoneNumber(this.phoneNumber, mobilePhoneNumber);
        this.mobilePhoneNumber = validatePhoneNumber(mobilePhoneNumber);
    }

    public void setEmail(String email) {
        this.email = validateEmail(email);
    }
}
