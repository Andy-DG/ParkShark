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

    public CreateDivisionDTO(String name, String originalName, int directorId) {
        this.name = name;
        this.originalName = originalName;
        this.directorId = directorId;
        this.headDivisionId = 0;
    }
}
