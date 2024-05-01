package com.example.notiumb.service;

import com.example.notiumb.repository.IMesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MesaService {

    @Autowired
    private IMesaRepository imesaRepository;

}
