package com.controlaltinsert.parkshark.alloction.service;

import com.controlaltinsert.parkshark.alloction.api.dto.AllocationDTO;
import com.controlaltinsert.parkshark.alloction.api.dto.CreateAllocationDTO;
import com.controlaltinsert.parkshark.alloction.domain.Allocation;
import com.controlaltinsert.parkshark.alloction.domain.AllocationRepository;
import com.controlaltinsert.parkshark.member.api.dto.MemberDTO;
import com.controlaltinsert.parkshark.member.service.MemberService;
import com.controlaltinsert.parkshark.parkinglot.api.dto.ParkingLotDTO;
import com.controlaltinsert.parkshark.parkinglot.service.ParkingLotService;
import com.controlaltinsert.parkshark.support.licenseplate.api.LicensePlateDTO;
import com.controlaltinsert.parkshark.support.licenseplate.service.LicensePlateService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class AllocationService {
    MemberService memberService;
    LicensePlateService licensePlateService;
    ParkingLotService parkingLotService;
    AllocationMapper allocationMapper;
    AllocationRepository allocationRepository;
    public AllocationDTO allocate(CreateAllocationDTO createAllocationDTO) {
        MemberDTO memberDTO = memberService.getMemberById(createAllocationDTO.getMemberId());
        LicensePlateDTO licensePlateDTO = licensePlateService.getLicenseById(createAllocationDTO.getLicensePlateId());
        ParkingLotDTO parkingLotDTO = parkingLotService.getParkingLotById(createAllocationDTO.getParkingLotId());
        LocalDate beginDate = LocalDate.now();
        LocalTime beginTime = LocalTime.now();
        Allocation allocation = allocationMapper.toEntity(memberDTO, licensePlateDTO, parkingLotDTO, beginDate, beginTime, null, null);
        allocationRepository.save(allocation);
        return allocationMapper.toDTO(allocation);
    }
}
