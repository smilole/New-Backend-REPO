package com.example.Backend.OfficeFunctional;

import com.example.Backend.UserFunctional.UserViewDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OfficeUsers {
    private List<UserViewDTO> users;

    public OfficeUsers(){
        this.users = new ArrayList<>();
    }
}
