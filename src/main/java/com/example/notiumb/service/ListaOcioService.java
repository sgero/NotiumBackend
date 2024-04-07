package com.example.notiumb.service;

import com.example.notiumb.converter.IEventoMapper;
import com.example.notiumb.converter.IListaOcioMapper;
import com.example.notiumb.repository.IEventoRepository;
import com.example.notiumb.repository.IListaOcioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListaOcioService {

    @Autowired
    private IListaOcioRepository repository;
    @Autowired
    private IEventoRepository eventoRepository;
    @Autowired
    private IListaOcioMapper converter;
    @Autowired
    private IEventoMapper eventoMapper;

}
