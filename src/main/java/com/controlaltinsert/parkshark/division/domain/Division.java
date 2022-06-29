package com.controlaltinsert.parkshark.division.domain;

import com.controlaltinsert.parkshark.employee.Employee;

import javax.persistence.*;

@Entity
public class Division {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DIVISION_SEQUENCE")
    @SequenceGenerator(name = "DIVISION_SEQUENCE", sequenceName = "division_sequence", allocationSize = 1)
    private int id;

    @Column
    private String name;

    @Column
    private String originalName;

    @OneToOne
    @JoinColumn(name = "director_id")
    private Employee director;

    public Employee getDirector() {
        return director;
    }

    public void setDirector(Employee director) {
        this.director = director;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }
}
