package com.example.notiumb.service;

import com.example.notiumb.repository.ITurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TurnoService {

    @Autowired
    private ITurnoRepository iTurnoRepository;

}
