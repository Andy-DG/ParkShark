package com.controlaltinsert.parkshark.division;

import com.controlaltinsert.parkshark.employee.Employee;

import javax.persistence.*;

@Entity
public class Division {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DIVISION_SEQUENCE")
    @SequenceGenerator(name = "DIVISION_SEQUENCE", sequenceName = "division_sequence", allocationSize = 1)
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
