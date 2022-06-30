package com.controlaltinsert.parkshark.member.domain;

import com.controlaltinsert.parkshark.support.address.domain.Address;
import com.controlaltinsert.parkshark.support.licenseplate.api.LicensePlateDTO;
import com.controlaltinsert.parkshark.support.licenseplate.domain.LicensePlate;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "member", schema = "parkshark")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Member {

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
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.phone = phone;
        this.email = email;
        this.address = fk_address_id;
        this.registrationDate = registrationDate;
        this.licensePlate = licensePlate;
    }
}

