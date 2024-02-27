package com.example.Backend.OfficeFunctional;

import com.example.Backend.Errors.AppException;
import com.example.Backend.Relations.UserToOffice;
import com.example.Backend.Relations.UserToOfficeRepository;
import com.example.Backend.TokenFunctional.TokenEntity;
import com.example.Backend.TokenFunctional.TokenRepository;
import com.example.Backend.UserFunctional.*;
import com.example.Backend.statusCode.StatusCode;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IOfficeServiceImpl implements IOfficeService {
    private final OfficeRepository officeRepository;
    private final TokenRepository tokenRepository;
    private final UserToOfficeRepository userToOfficeRepository;
    private final UserRepository userRepository;

    @SneakyThrows
    @Override
    public OfficeDTO createOffice(OfficeCreateDTO officeCreateDTO, String tokenValue) {
        Optional<TokenEntity> tokenEntityOptional = tokenRepository.findByValue(tokenValue);
        if(tokenEntityOptional.isEmpty()){
            throw new AppException(401, "Unauthorized");
        }
        OfficeEntity office = new OfficeEntity(officeCreateDTO.getName(), tokenEntityOptional.get().getUserid());
        if(officeRepository.findByAdministratorId(tokenEntityOptional.get().getUserid()).isPresent()){
            throw new AppException(400, "Пользователь не может иметь больше одного офиса");
        }
        officeRepository.save(office);
        userToOfficeRepository.save(new UserToOffice(
                userRepository.findById(tokenEntityOptional.get().getUserid()).get(),
                officeRepository.findByAdministratorId(tokenEntityOptional.get().getUserid()).get(),
                "Administrator"));
        return new OfficeDTO(officeRepository.findByAdministratorId(tokenEntityOptional.get().getUserid()).get());
    }

    @SneakyThrows
    @Override
    public StatusCode appointment(UUID Officeid, UserAppointmentDTO userAppointmentDTO, String tokenValue) {
        Optional<TokenEntity> tokenEntityOptional = tokenRepository.findByValue(tokenValue);
        if(tokenEntityOptional.isEmpty()){
            throw new AppException(401, "Unauthorized.");
        }
        if(officeRepository.findById(Officeid).isEmpty()){
            throw new AppException(400, "Офис не найден.");
        }
        Optional<UserToOffice> userEntityOptional = userToOfficeRepository.findByUserId(tokenEntityOptional.get().getUserid());
        if(userEntityOptional.isPresent() && (userEntityOptional.get().getRole().equals("Administrator") ||
                userEntityOptional.get().getRole().equals("Office employee"))){
            if(userRepository.findById(userAppointmentDTO.getId()).isEmpty()) {
                throw new AppException(400, "Пользователь не найден");
            }
            if(userAppointmentDTO.getRole() == null || userAppointmentDTO.getRole().equals("")){
                throw new AppException(400, "Поле роль не может быть пустым");
            }
            UserEntity newUserEntity = userRepository.findById(userAppointmentDTO.getId()).get();
            UserToOffice userToOffice = new UserToOffice(newUserEntity, officeRepository.findById(Officeid).get(), userAppointmentDTO.getRole());
            userToOfficeRepository.save(userToOffice);
            return new StatusCode(200, "Пользователь добавлен.");
        }
        else{
            throw new AppException(403, "У вас нет прав, чтобы добавлять участников.");
        }
    }

    @Override
    public OfficeUsers viewAll(String tokenValue, UUID officeid) {
        var users = userToOfficeRepository.findAllByOfficeId(officeid);
        OfficeUsers userOfficeDTOS = new OfficeUsers();
        users.forEach(user->{
            userOfficeDTOS.getUsers().add(new UserViewDTO(userRepository.findById(user.get().getUser().getId()).get(), user.get().getRole()));
        });
        return userOfficeDTOS;
    }
}
