package com.controlaltinsert.parkshark.employee;


import com.controlaltinsert.parkshark.support.address.domain.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "employee")
@NoArgsConstructor
@Getter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
    @SequenceGenerator(name = "employee_seq")
    private int id;

    private String firstName;
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    private String phoneNumber;
    private String cellPhoneNumber;
    private String email;



    public void setId(int id) {
        this.id = id;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
