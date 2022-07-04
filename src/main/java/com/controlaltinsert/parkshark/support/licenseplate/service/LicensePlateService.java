package com.controlaltinsert.parkshark.support.licenseplate.service;

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
}
