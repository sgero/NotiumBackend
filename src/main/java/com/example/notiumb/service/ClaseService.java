package com.example.notiumb.service;

import com.example.notiumb.converter.IClaseMapper;
import com.example.notiumb.dto.ClaseDTO;
import com.example.notiumb.repository.IClaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaseService {

    @Autowired
    private IClaseRepository iClaseRepository;

    @Autowired
    private IClaseMapper iClaseMapper;

    public List<ClaseDTO> getAll(){
        return iClaseMapper.toDTO(iClaseRepository.findAll());
    }
}
