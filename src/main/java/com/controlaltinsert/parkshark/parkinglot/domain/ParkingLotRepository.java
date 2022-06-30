package com.controlaltinsert.parkshark.parkinglot.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface ParkingLotRepository extends JpaRepository<ParkingLot, Integer> {

}
