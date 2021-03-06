package com.controlaltinsert.parkshark.support.address.domain;

import com.controlaltinsert.parkshark.support.postalcode.domain.PostalCode;
import com.controlaltinsert.parkshark.util.Validate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

@Entity
@Table(name = "address", schema = "parkshark")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_id_seq")
    @SequenceGenerator(name = "address_id_seq", sequenceName = "address_id_seq", allocationSize = 1)
    private int id;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "street_number")
    private int streetNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_postal_code_id")
    private PostalCode postalCode;

    public Address(String streetName, int streetNumber, PostalCode postalCode) {
        this.streetName = validateStreetName(streetName);
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
    }

    public String validateStreetName(String streetName) {
        Validate.validateString(streetName, "Address street name validated successfully");
        return streetName;
}

}
