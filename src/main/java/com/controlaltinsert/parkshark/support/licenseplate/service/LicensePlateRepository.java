package com.controlaltinsert.parkshark.support.licenseplate.service;

import com.controlaltinsert.parkshark.support.licenseplate.domain.LicensePlate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicensePlateRepository extends JpaRepository<LicensePlate, Integer> {
}
