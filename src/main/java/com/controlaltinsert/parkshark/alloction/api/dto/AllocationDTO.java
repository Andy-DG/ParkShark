package com.controlaltinsert.parkshark.alloction.api.dto;

import com.controlaltinsert.parkshark.member.api.dto.MemberDTO;
import com.controlaltinsert.parkshark.member.domain.Member;
import com.controlaltinsert.parkshark.parkinglot.api.dto.ParkingLotDTO;
import com.controlaltinsert.parkshark.parkinglot.domain.ParkingLot;
import com.controlaltinsert.parkshark.support.licenseplate.api.LicensePlateDTO;
import com.controlaltinsert.parkshark.support.licenseplate.domain.LicensePlate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class AllocationDTO {
    int id;
    MemberDTO memberDTO;
    LicensePlateDTO licensePlateDTO;
    ParkingLotDTO parkingLotDTO;
    LocalDate beginDate;
    LocalTime beginTime;
    LocalDate endDate;
    LocalTime endTime;
}
