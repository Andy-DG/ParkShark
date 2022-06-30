package com.controlaltinsert.parkshark.parkinglot.api.dto;


import com.controlaltinsert.parkshark.employee.api.EmployeeDTO;
import com.controlaltinsert.parkshark.parkinglot.domain.Category;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ParkingLotDTO {

    private int id;
    private String name;

    private Category category;
    private int maxCapacity;
    private double pricePerHour;
    private EmployeeDTO contactPerson;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public EmployeeDTO getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(EmployeeDTO contactPerson) {
        this.contactPerson = contactPerson;
    }

}
