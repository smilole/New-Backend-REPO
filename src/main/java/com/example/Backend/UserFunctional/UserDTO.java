package com.example.Backend.UserFunctional;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String fullname;
    private String login;
    private String password;

    public UserDTO(UserEntity userEntity){
        this.fullname = userEntity.getFullname();
        this.login = userEntity.getLogin();
    }

    public UserDTO(){}
}
