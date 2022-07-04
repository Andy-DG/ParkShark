package com.controlaltinsert.parkshark.alloction.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class CreateAllocationDTO {
    int memberId;
    int licensePlateId;
    int parkingLotId;
}
