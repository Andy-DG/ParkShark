package com.controlaltinsert.parkshark.alloction.domain;

import com.controlaltinsert.parkshark.member.domain.Member;
import com.controlaltinsert.parkshark.parkinglot.domain.ParkingLot;
import com.controlaltinsert.parkshark.support.licenseplate.domain.LicensePlate;
import com.controlaltinsert.parkshark.util.Validate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "allocation", schema = "parkshark")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class Allocation {
    @Transient
    private final Logger allocationLogger = LoggerFactory.getLogger(Allocation.class);

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "allocation_id_seq")
    @SequenceGenerator(name = "allocation_id_seq", sequenceName = "allocation_id_seq", allocationSize = 1)
    int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_member_id")
    Member member;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_license_plate")
    LicensePlate licensePlate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_parking_lot")
    ParkingLot parkingLot;

    @Column(name = "begin_date")
    LocalDate beginDate;

    @Column(name = "begin_time")
    LocalTime beginTime;

    @Column(name = "end_date")
    LocalDate endDate;

    @Column(name = "end_time")
    LocalTime endTime;

    public Allocation(Member member, LicensePlate licensePlate, ParkingLot parkingLot, LocalDate beginDate, LocalTime beginTime, LocalDate endDate, LocalTime endTime) {
        this.member = validateMember(member);
        this.licensePlate = validateLicensePlate(licensePlate);
        this.parkingLot = validateParkingLot(parkingLot);
        this.beginDate = validateBeginDate(beginDate);
        this.beginTime = validateBeginTime(beginTime);
        this.endDate = endDate;
        this.endTime = endTime;
    }

    public Member validateMember(Member member) {
        Validate.objectNotNull(member, "An allocation must have a member");
        return member;
    }

    public LicensePlate validateLicensePlate(LicensePlate licensePlate) {
        Validate.objectNotNull(licensePlate, "An allocation must have a license plate");
        return licensePlate;
    }

    public ParkingLot validateParkingLot(ParkingLot parkingLot) {
        Validate.objectNotNull(parkingLot, "An allocation must have a parking-lot");
        return parkingLot;
    }

    public LocalDate validateBeginDate(LocalDate beginDate) {
        Validate.objectNotNull(beginDate, "An allocation must have a begin date");
        return beginDate;
    }

    public LocalTime validateBeginTime(LocalTime beginTime) {
        Validate.objectNotNull(beginTime, "An allocation must have a begin time");
        return beginTime;
    }




}
