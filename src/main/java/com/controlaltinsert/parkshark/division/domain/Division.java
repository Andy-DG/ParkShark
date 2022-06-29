package com.controlaltinsert.parkshark.division.domain;

import com.controlaltinsert.parkshark.employee.Employee;
import com.controlaltinsert.parkshark.parkinglot.domain.ParkingLot;
import lombok.*;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@ToString
public class Division {

    private static final Logger divisionLogger = LoggerFactory.getLogger(Division.class.getName());

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DIVISION_SEQUENCE")
    @SequenceGenerator(name = "DIVISION_SEQUENCE", sequenceName = "division_sequence", allocationSize = 1)
    private final int id;

    @Column
    private String name;

    @Column
    private String originalName;

    @OneToOne
    @JoinColumn(name = "director_id")
    private Employee director;

    public Division(String name, String originalName, Employee director){
        this.name = validateName(name);
        this.originalName = validateName(originalName);
        this.director = director;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o != null) {
            Hibernate.getClass(this);
            Hibernate.getClass(o);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    private String validateName(String name){
        if(name == null || name.isBlank()){
            String message = "Name cannot be null";
            divisionLogger.error(message);
            throw new IllegalArgumentException(message);
        }
        return name;
    }
}
