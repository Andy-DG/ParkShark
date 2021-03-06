package com.controlaltinsert.parkshark.division.domain;

import com.controlaltinsert.parkshark.employee.domain.Employee;
import com.controlaltinsert.parkshark.util.Validate;
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

    @Column(name = "fk_head_division_id")
    private int fkHeadDivisionId;

    public int getFkHeadDivisionId() {
        return fkHeadDivisionId;
    }

    public void setDirector(Employee director) {
        this.director = director;
    }

    public Division(String name, String originalName, Employee director) {
        this.name = Validate.validateString(name, "Division-name");
        this.originalName = Validate.validateString(originalName, "Original division-name");
        this.director = director;
        this.fkHeadDivisionId = 0;
    }

    public Division(String name, String originalName, Employee director, int fkHeadDivisionId){
        this(name,originalName,director);
        this.fkHeadDivisionId = fkHeadDivisionId;
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

}
