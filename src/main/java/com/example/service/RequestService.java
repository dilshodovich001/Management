package com.example.service;

import com.example.dto.RequestDTO;
import com.example.entity.DistrictEntity;
import com.example.entity.RequestEntity;
import com.example.exceptions.ItemAlreadyExistException;
import com.example.exceptions.ItemNotFoundException;
import com.example.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;
    private final DistrictService districtService;

    public RequestDTO addRequest(RequestDTO dto) {
        RequestEntity exists = requestRepository.findByProductIdAndDistrictId(dto.getProductId(),
                dto.getDistrictId());
        DistrictEntity district = districtService.get(dto.getDistrictId());
        if (district == null) {
            throw new ItemNotFoundException("Not found District");
        }
        if (exists != null) {
            throw new ItemAlreadyExistException("Already exists");
        }
        RequestEntity entity = new RequestEntity();
        entity.setProductId(dto.getProductId());
        entity.setDistrictId(dto.getDistrictId());
        entity.setPlaceName(dto.getPlaceName());
        requestRepository.save(entity);
        return dto;
    }
    public RequestEntity get(String id){
       return requestRepository.findById(id).orElseThrow(
                () ->new ItemNotFoundException("Not Found Request"));
    }

    public List<RequestDTO> requestList() {
        List<RequestDTO> dtos = new ArrayList<>();
        for (RequestEntity requestEntity : requestRepository.findAll()) {
            RequestDTO requestDTO = new RequestDTO();
            requestDTO.setId(requestEntity.getId());
            requestDTO.setDistrictId(requestEntity.getDistrictId());
            requestDTO.setPlaceName(requestEntity.getPlaceName());
            requestDTO.setProductId(requestEntity.getProductId());
            dtos.add(requestDTO);
        }
        return dtos;
    }

    public int update(String id, RequestDTO requestDTO) {
        get(id);
        return requestRepository.update(id,requestDTO.getPlaceName()
        ,requestDTO.getProductId(),requestDTO.getDistrictId());
    }

    public void delete(String id) {
        get(id);
        requestRepository.deleteById(id);
    }
}
