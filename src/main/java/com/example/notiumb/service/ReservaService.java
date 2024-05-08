package com.example.notiumb.service;

import com.example.notiumb.repository.IReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaService {

    @Autowired
    private IReservaRepository IReservaRepository;
}
