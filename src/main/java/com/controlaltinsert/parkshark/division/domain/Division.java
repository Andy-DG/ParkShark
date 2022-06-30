package com.controlaltinsert.parkshark.division.domain;

import com.controlaltinsert.parkshark.employee.domain.Employee;
import lombok.*;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@ToString
@AllArgsConstructor
@Table(name = "DIVISION", schema = "parkshark")
public class Division {
    @Transient
    private static final Logger divisionLogger = LoggerFactory.getLogger(Division.class.getName());

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DIVISION_SEQUENCE")
    @SequenceGenerator(name = "DIVISION_SEQUENCE", sequenceName = "DIVISION_id_seq", allocationSize = 1)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "original_name")
    private String originalName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_director_id")
    private Employee director;

    @ManyToOne
    @JoinColumn(name = "fk_head_division_id")
    private Division headDivision;

    public void setHeadDivision(Division headDivision) {
        this.headDivision = headDivision;
    }

    public void setDirector(Employee director) {
        this.director = director;
    }

    public Division(String name, String originalName, Employee director){
        this.name = validateName(name);
        this.originalName = validateName(originalName);
        this.director = director;}

    public Division(String name, String originalName, Employee director, Division headDivision) {
        this(name, originalName, director);
        this.headDivision = validateHeadDivision(headDivision);
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

    private Division validateHeadDivision(Division headDivision){
        if(headDivision.getHeadDivision().getId() != 0){
            throw new IllegalStateException("Target head division is a subdivision");
        }
        return headDivision;
    }
}
