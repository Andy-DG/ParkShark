package com.controlaltinsert.parkshark.division.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@Data
@EqualsAndHashCode
public class CreateDivisionDTO {

    private String name;

    private String originalName;

    private int directorId;

}
