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

    private String



    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

}
