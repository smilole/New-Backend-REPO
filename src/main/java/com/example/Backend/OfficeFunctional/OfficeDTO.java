package com.example.Backend.OfficeFunctional;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class OfficeDTO {
    private UUID id;
    private String name;
    private UUID administratorId;

    public OfficeDTO(){}
    public OfficeDTO(OfficeEntity officeEntity){
        this.id = officeEntity.getId();
        this.name = officeEntity.getName();
        this.administratorId = officeEntity.getAdministratorId();
    }
}
