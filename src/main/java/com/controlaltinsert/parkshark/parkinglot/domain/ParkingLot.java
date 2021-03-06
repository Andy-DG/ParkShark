package com.controlaltinsert.parkshark.parkinglot.domain;

import com.controlaltinsert.parkshark.division.domain.Division;
import com.controlaltinsert.parkshark.employee.domain.Employee;
import com.controlaltinsert.parkshark.util.Validate;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

@Entity
@Table(name = "PARKING_LOT", schema = "parkshark")
@NoArgsConstructor
@Getter
public class ParkingLot {
    private static final Logger parkingLotLogger = LoggerFactory.getLogger(ParkingLot.class.getName());

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARKING_LOT_ID_SEQ")
    @SequenceGenerator(name = "PARKING_LOT_ID_SEQ", sequenceName = "PARKING_LOT_id_seq", allocationSize = 1)
    private int id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "fk_category_id")
    private Category category;

    @Column(name = "max_capacity")
    private int maxCapacity;

    @Column(name = "price_per_hour")
    private double pricePerHour;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_contact_id")
    private Employee contactPerson;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_division_id")
    private Division division;


    public ParkingLot(String name, Category category, int maxCapacity, double pricePerHour, Employee contactPerson, Division division) {
        this.name = Validate.validateString(name, "Parking-lot name");
        this.category = validateCategory(category);
        this.maxCapacity = validateMaxCapacity(maxCapacity);
        this.pricePerHour = validatePricePerHour(pricePerHour);
        this.contactPerson = validateEmployee(contactPerson);
        this.division = validateDivision(division);
        parkingLotLogger.info("Parkinglot: " + name + " is created");
    }

    private Division validateDivision(Division division) {
        Validate.objectNotNull(division, "Division does not exist");
        return division;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String setName(String name) {
        this.name = Validate.validateString(name, "Parking-lot name");
        return this.name;
    }

    public void setCategory(Category category) {
        this.category = validateCategory(category);
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = validateMaxCapacity(maxCapacity);
    }

    private int validateMaxCapacity(int maxCapacity) {
        if(maxCapacity <= 0){
            String message = "Max capacity must be more than zero";
            parkingLotLogger.error(message);
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
            parkingLotLogger.error(message);
            throw new IllegalArgumentException(message);
        }
        return pricePerHour;
    }

    public void setContactPerson(Employee contactPerson) {
        this.contactPerson = validateEmployee(contactPerson);
    }

    private Employee validateEmployee(Employee contactPerson) {
        Validate.objectNotNull(contactPerson, "Contact person cannot be empty");
        return contactPerson;
    }

    private Category validateCategory(Category category){
        Validate.objectNotNull(category, "Category cannot be empty");
        return category;
    }
}
