package com.example.Backend.OfficeFunctional;

import com.example.Backend.UserFunctional.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OfficeRepository extends JpaRepository<OfficeEntity, UUID> {
    Optional<OfficeEntity> findByAdministratorId(UUID administratorID);
}
