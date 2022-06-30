package com.controlaltinsert.parkshark.support.licenseplate.service;

import com.controlaltinsert.parkshark.member.api.CreateMemberDTO;
import com.controlaltinsert.parkshark.support.licenseplate.api.CreateLicensePlateDTO;
import com.controlaltinsert.parkshark.support.licenseplate.api.LicensePlateDTO;
import com.controlaltinsert.parkshark.support.licenseplate.domain.LicensePlate;
import org.springframework.stereotype.Component;

@Component
public class LicensePlateMapper {
    public LicensePlate toEntity(CreateLicensePlateDTO createLicensePlateDTO) {
        return new LicensePlate(createLicensePlateDTO.getLicensePlate(), createLicensePlateDTO.getCountry());
    }

    public LicensePlateDTO toDTO(LicensePlate licensePlate) {
        return new LicensePlateDTO(licensePlate.getId(), licensePlate.getLicensePlate(), licensePlate.getCountry());
    }
}
