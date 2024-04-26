package com.example.notiumb.service;

import com.example.notiumb.repository.IClaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClaseService {

    @Autowired
    private IClaseRepository iClaseRepository;
}
