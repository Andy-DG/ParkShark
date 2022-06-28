package com.controlaltinsert.parkshark.parkinglot.domain;

import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

@Entity
@Table(name = "PARKING_LOT")
@NoArgsConstructor
@Getter
public class ParkingLot {
    private static Logger logger = LoggerFactory.getLogger(ParkingLot.class.getName());

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARKING_LOT_ID_SEQ")
    @SequenceGenerator(name = "PARKING_LOT_ID_SEQ", sequenceName = "PARKING_LOT_ID_SEQ", allocationSize = 1)
    private int id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "fk_category_id")
    private Category category;

    @Column(name = "max_capacity")
    private int maxCapacity;

    @Column(name = "price_per_hour")
    private double pricePerHour;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_contact_id")
    private Employee contactPerson;

    public ParkingLot(String name, Category category, int maxCapacity, double pricePerHour, Employee contactPerson) {
        this.name = name;
        this.category = category;
        this.maxCapacity = maxCapacity;
        this.pricePerHour = pricePerHour;
        this.contactPerson = contactPerson;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = validateName(name);
    }

    public void setCategory(Category category) {
        this.category = validateCategory(category);
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = validateMaxCapacity(maxCapacity);
    }

    private int validateMaxCapacity(int maxCapacity) {
        if(maxCapacity < 0){
            String message = "Max capacity must be more than zero";
            logger.error(message);
            throw new IllegalArgumentException(message);
        }
        return maxCapacity;
    }

    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = validatePricePerHour(pricePerHour);
    }

    private double validatePricePerHour(double pricePerHour) {
        if(pricePerHour <= 0){
            String message = "Price per hour cannot be less than or equal to zero";
            logger.error(message);
            throw new IllegalArgumentException(message);
        }
        return pricePerHour;
    }

    public void setContactPerson(Employee contactPerson) {
        this.contactPerson = validateEmployee(contactPerson);
    }

    private Employee validateEmployee(Employee contactPerson) {
        if(contactPerson == null){
            String message = "ContactPerson cannot be null";
            logger.error(message);
            throw new IllegalArgumentException(message);
        }
        return contactPerson;
    }


    private String validateName(String name){
        if(name == null || name.isBlank()){
            String message = "Name cannot be null";
            logger.error(message);
            throw new IllegalArgumentException(message);
        }
        return name;
    }

    private Category validateCategory(Category category){
        if(category == null){
            String message = "Category cannot be null";
            logger.error(message);
            throw new IllegalArgumentException(message)
        }
        return category;
    }
}
