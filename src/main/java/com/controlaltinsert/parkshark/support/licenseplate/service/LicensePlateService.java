package com.controlaltinsert.parkshark.support.licenseplate.service;

import com.controlaltinsert.parkshark.member.api.dto.MemberDTO;
import com.controlaltinsert.parkshark.member.domain.Member;
import com.controlaltinsert.parkshark.support.licenseplate.api.LicensePlateDTO;
import com.controlaltinsert.parkshark.support.licenseplate.domain.LicensePlate;
import com.controlaltinsert.parkshark.util.Validate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LicensePlateService {
    LicensePlateMapper licensePlateMapper;
    LicensePlateRepository licensePlateRepository;

    public LicensePlateDTO getLicenseById(int licensePlateId) {
            LicensePlate licensePlate = licensePlateRepository.findById(licensePlateId).orElse(null);
            Validate.objectNotNull(licensePlate, "License not found!");
            return licensePlateMapper.toDTO(licensePlate);
    }
}
