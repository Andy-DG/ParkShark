package com.controlaltinsert.parkshark.division.api.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class CreateDivisionDTO {

    private String name;

    private String originalName;

    private int directorId;

    private int headDivisionId;

}
