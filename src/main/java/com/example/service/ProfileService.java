package com.example.service;

import com.example.dto.CarrierDTO;
import com.example.dto.ProfileDTO;
import com.example.entity.ProductEntity;
import com.example.entity.ProfileEntity;
import com.example.enums.ProfileRole;
import com.example.exceptions.AppForbiddenException;
import com.example.exceptions.ItemAlreadyExistException;
import com.example.exceptions.ItemNotFoundException;
import com.example.exceptions.ProfileCreateException;
import com.example.repository.CarrierRegionPlaceRepository;
import com.example.repository.ProfileRepository;
import com.example.util.MD5Util;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;


    public String addProfile(ProfileDTO carrierDTO) {
        Optional<ProfileEntity> optional =
                profileRepository.findByPhone(carrierDTO.getPhone());

        if (optional.isPresent()) {
            throw new ItemAlreadyExistException("Item already exist");
        }


        ProfileEntity profile = new ProfileEntity();
        profile.setName(carrierDTO.getName());
        profile.setSurname(carrierDTO.getSurname());
        profile.setPhone(carrierDTO.getPhone());
        profile.setEmail(carrierDTO.getEmail());
        profile.setPassword(MD5Util.encode(carrierDTO.getPassword()));
        profile.setRole(ProfileRole.ROLE_CARRIER);

        profileRepository.save(profile);

        if (profile.getId() == 0) {
            throw new ProfileCreateException("Something went wrong");
        }

        return "Profile successfully added";
    }

    public List<ProfileDTO> profileList() {
        List<ProfileDTO> dtos = new ArrayList<>();
        for (ProfileEntity profileEntity : profileRepository.findAll()) {
            ProfileDTO profileDTO = new ProfileDTO();
            profileDTO.setId(profileEntity.getId());
            profileDTO.setName(profileEntity.getName());
            profileDTO.setSurname(profileEntity.getSurname());
            profileDTO.setPhone(profileEntity.getPhone());
            profileDTO.setEmail(profileEntity.getEmail());
            profileDTO.setPassword(profileEntity.getPassword());
            profileDTO.setRole(profileEntity.getRole());
            dtos.add(profileDTO);
        }
        return dtos;
    }

    public void delete(Integer id) {
        get(id);
        profileRepository.deleteById(id);
    }

    public ProfileEntity get(Integer id) {
        return profileRepository.findById(id).
                orElseThrow(() -> new ItemNotFoundException
                        ("Profile Not Found"));
    }

    public ProfileDTO updateAdmin(Integer id, ProfileDTO profileDTO) {
        get(id);
        profileRepository.updateAdminById(profileDTO.getName(), profileDTO.getSurname(), profileDTO.getVisible()
                , profileDTO.getRole(), profileDTO.getStatus(), id);

        return profileDTO;
    }

    public ProfileEntity getCarrier(Integer id){
       return profileRepository.findByIdAndRole(id);
    }


}
