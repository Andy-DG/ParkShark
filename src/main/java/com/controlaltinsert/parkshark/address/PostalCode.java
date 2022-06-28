package com.controlaltinsert.parkshark.address;

import javax.persistence.*;

@Entity
public class PostalCode {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POSTAL_CODE_GENERATOR")
    @SequenceGenerator(name = "POSTAL_CODE_GENERATOR", sequenceName = "postal_code_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    private String zipCode;

    private String city;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
