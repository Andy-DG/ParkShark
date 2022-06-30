package com.controlaltinsert.parkshark.support.licenseplate.service;

import com.controlaltinsert.parkshark.support.licenseplate.api.CreateLicensePlateDTO;
import com.controlaltinsert.parkshark.support.licenseplate.api.LicensePlateDTO;
import com.controlaltinsert.parkshark.support.licenseplate.domain.LicensePlate;
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

    LicensePlateDTO createLicensePlate(CreateLicensePlateDTO createLicensePlateDTO) {
        LicensePlate licensePlate = this.licensePlateMapper.toEntity(createLicensePlateDTO);
        this.licensePlateRepository.save(licensePlate);
        return this.licensePlateMapper.toDTO(licensePlate);
    }


}
