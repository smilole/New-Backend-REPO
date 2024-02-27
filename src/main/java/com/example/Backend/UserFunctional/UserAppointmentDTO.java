package com.example.Backend.UserFunctional;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class UserAppointmentDTO {
    private UUID id;
    private String Role;
}
