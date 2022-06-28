package com.controlaltinsert.parkshark.support.postalcode.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class PostalCode {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POSTAL_CODE_GENERATOR")
    @SequenceGenerator(name = "POSTAL_CODE_GENERATOR", sequenceName = "postal_code_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "city")
    private String city;

    public PostalCode(String zipCode, String city) {
        this.zipCode = zipCode;
        this.city = city;
    }
}
