package com.controlaltinsert.parkshark.parkinglot.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Transient;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ParkingLotRepository extends JpaRepository<ParkingLot, Integer> {

}
