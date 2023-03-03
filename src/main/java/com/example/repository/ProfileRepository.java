package com.example.repository;

import com.example.entity.ProfileEntity;
import com.example.enums.ProfileRole;
import com.example.enums.ProfileStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ProfileRepository extends CrudRepository<ProfileEntity, Integer> {
    Optional<ProfileEntity> findByPhone(String phone);

    ProfileEntity findByPhoneAndPassword(String phone, String password);

    @Transactional
    @Modifying
    @Query("update ProfileEntity set name = ?1, surname = ?2 , visible = ?3 , role = ?4 , status = ?5 where id = ?6")
    void updateAdminById(String name, String surname, Boolean visible, ProfileRole role, ProfileStatus status, Integer id);

    @Query("from ProfileEntity p where p.id = ?1 and p.role ='ROLE_CARRIER'")
    ProfileEntity findByIdAndRole(Integer id);

    @Transactional
    @Modifying
    @Query("update ProfileEntity p set p.visible = false where p.id = ?1")
    void deleteProfile(Integer id);
}
