package com.controlaltinsert.parkshark.alloction.service;

import com.controlaltinsert.parkshark.alloction.api.dto.AllocationDTO;
import com.controlaltinsert.parkshark.alloction.domain.Allocation;
import com.controlaltinsert.parkshark.member.api.dto.MemberDTO;
import com.controlaltinsert.parkshark.member.domain.Member;
import com.controlaltinsert.parkshark.member.service.MemberMapper;
import com.controlaltinsert.parkshark.parkinglot.api.dto.ParkingLotDTO;
import com.controlaltinsert.parkshark.parkinglot.domain.ParkingLot;
import com.controlaltinsert.parkshark.parkinglot.service.ParkingLotMapper;
import com.controlaltinsert.parkshark.support.licenseplate.api.LicensePlateDTO;
import com.controlaltinsert.parkshark.support.licenseplate.domain.LicensePlate;
import com.controlaltinsert.parkshark.support.licenseplate.service.LicensePlateMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AllocationMapper {
    MemberMapper memberMapper;
    LicensePlateMapper licensePlateMapper;
    ParkingLotMapper parkingLotMapper;

    public Allocation toEntity(MemberDTO memberDTO, LicensePlateDTO licensePlateDTO, ParkingLotDTO parkingLotDTO, LocalDate beginDate, LocalTime beginTime, LocalDate endDate, LocalTime endTime) {
        return new Allocation(
                memberMapper.toEntity(memberDTO),
                licensePlateMapper.toEntity(licensePlateDTO),
                parkingLotMapper.toEntity(parkingLotDTO),
                beginDate,
                beginTime,
                endDate,
                endTime
                );
    }

    public Allocation toEntity(AllocationDTO allocationDTO) {
        return new Allocation(
                allocationDTO.getId(),
                memberMapper.toEntity(allocationDTO.getMemberDTO()),
                licensePlateMapper.toEntity(allocationDTO.getLicensePlateDTO()),
                parkingLotMapper.toEntity(allocationDTO.getParkingLotDTO()),
                allocationDTO.getBeginDate(),
                allocationDTO.getBeginTime(),
                allocationDTO.getEndDate(),
                allocationDTO.getEndTime());
    }

    public AllocationDTO toDTO(Allocation allocation) {
        return new AllocationDTO(
                allocation.getId(),
                memberMapper.toDTO(allocation.getMember()),
                licensePlateMapper.toDTO(allocation.getLicensePlate()),
                parkingLotMapper.toDTO(allocation.getParkingLot()),
                allocation.getBeginDate(),
                allocation.getBeginTime(),
                allocation.getEndDate(),
                allocation.getEndTime());
    }

    public List<AllocationDTO> toDTO(List<Allocation> allocations) {
        return allocations.stream().map(this::toDTO).toList();
    }

}
