package com.controlaltinsert.parkshark.support.licenseplate.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "license_plate", schema = "parkshark")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LicensePlate {
    @Id
    @SequenceGenerator(name = "member_id_seq", sequenceName = "member_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "member_id_seq")
    int id;

    @Column(name="license_plate_number")
    String licensePlate;

    @Column(name="country")
    String country;



    public LicensePlate(String licensePlate, String country) {
        this.licensePlate = licensePlate;
        this.country = country;
    }
}
