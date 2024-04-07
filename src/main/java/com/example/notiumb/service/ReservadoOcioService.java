package com.example.notiumb.service;

import com.example.notiumb.converter.IEventoMapper;
import com.example.notiumb.converter.IReservadoOcioMapper;
import com.example.notiumb.repository.IEventoRepository;
import com.example.notiumb.repository.IReservadoOcioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservadoOcioService {

    @Autowired
    private IReservadoOcioRepository repository;
    @Autowired
    private IEventoRepository eventoRepository;
    @Autowired
    private IReservadoOcioMapper converter;
    @Autowired
    private IEventoMapper eventoMapper;



}
