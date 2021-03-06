package com.controlaltinsert.parkshark.support.postalcode.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "POSTAL_CODE", schema = "parkshark")
public class PostalCode {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "postal_code_id_seq")
    @SequenceGenerator(name = "postal_code_id_seq", sequenceName = "postal_code_id_seq", allocationSize = 1)
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
