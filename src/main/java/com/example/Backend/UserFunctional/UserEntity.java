package com.example.Backend.UserFunctional;

import com.example.Backend.OfficeFunctional.OfficeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "Users")
public class UserEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    private String fullname;
    @Column(unique = true)
    private String login;
    private String password;

    public UserEntity(UserDTO userDTO){
        this.fullname = userDTO.getFullname();
        this.login = userDTO.getLogin();
        this.password = userDTO.getPassword();
    }
    public UserEntity(){

    }
}
