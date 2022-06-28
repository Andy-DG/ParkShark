package com.controlaltinsert.parkshark.address;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq")
    @SequenceGenerator(name = "address_seq")
    private Long id;

    private String streetname;

    private int houseNumber;

    private PostalCode postalCode;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
