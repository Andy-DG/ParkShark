package com.controlaltinsert.parkshark.member.domain;

import com.controlaltinsert.parkshark.support.address.domain.Address;
import com.controlaltinsert.parkshark.support.licenseplate.api.LicensePlateDTO;
import com.controlaltinsert.parkshark.support.licenseplate.domain.LicensePlate;
import com.controlaltinsert.parkshark.util.Validate;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Cascade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.time.LocalDate;

import static com.controlaltinsert.parkshark.util.Validate.*;

@Entity
@Table(name = "member", schema = "parkshark")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Member {
    static Logger memberLogger = LoggerFactory.getLogger(Member.class);

    @Id
    @SequenceGenerator(name = "member_id_seq", sequenceName = "member_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "member_id_seq")
    int id;

    @Column(name="first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "mobile_phone_number")
    String mobile;

    @Column(name = "phone_number")
    String phone;

    @Column(name = "email")
    String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_address_id")
    Address address;

    @Column(name = "registration_date")
    LocalDate registrationDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_license_plate_id")
    LicensePlate licensePlate;

    public Member(String firstName, String lastName, String mobile, String phone, String email, Address fk_address_id, LocalDate registrationDate, LicensePlate licensePlate) {
        this.firstName = validateString(firstName, "Firstname");
        this.lastName = validateString(lastName, "Lastname");
        this.mobile = validatePhoneNumberFormat(mobile);
        this.phone = validatePhoneNumberFormat(phone);
        this.email = validateEmail(email);
        this.address = validateAddress(fk_address_id);
        this.registrationDate = registrationDate;
        this.licensePlate = validateLicensePlate(licensePlate);
    }

    private LicensePlate validateLicensePlate(LicensePlate licensePlate) {
        if (licensePlate == null) {
            String errorMessage = "License plate can't be null";
            memberLogger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        return licensePlate;
    }
}

