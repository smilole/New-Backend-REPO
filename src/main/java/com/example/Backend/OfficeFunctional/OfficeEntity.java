package com.example.Backend.OfficeFunctional;

import com.example.Backend.UserFunctional.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Office")
@Getter
@Setter
public class OfficeEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    private String name;
    private UUID administratorId;


    public OfficeEntity(){}

    public OfficeEntity(String name, UUID id){
        this.name = name;
        this.administratorId = id;
    }
}
