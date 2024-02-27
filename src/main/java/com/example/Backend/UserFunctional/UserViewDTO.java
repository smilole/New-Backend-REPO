package com.example.Backend.UserFunctional;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserViewDTO {
    private String fullname;
    private String login;
    private String Role = "Пользователь";

    public UserViewDTO(UserEntity userEntity){
        this.fullname = userEntity.getFullname();
        this.login = userEntity.getLogin();
    }
    public UserViewDTO(UserEntity userEntity, String Role){
        this.fullname = userEntity.getFullname();
        this.login = userEntity.getLogin();
        this.Role = Role;
    }

    public UserViewDTO(){}
}
