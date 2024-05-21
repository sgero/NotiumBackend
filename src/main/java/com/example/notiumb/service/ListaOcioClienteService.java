package com.example.notiumb.service;

import com.example.notiumb.converter.IListaOcioClienteMapper;
import com.example.notiumb.model.ListaOcioCliente;
import com.example.notiumb.repository.IEventoRepository;
import com.example.notiumb.repository.IListaOcioClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListaOcioClienteService {

    @Autowired
    private IListaOcioClienteRepository repository;
    @Autowired
    private IEventoRepository eventoRepository;
    @Autowired
    private IListaOcioClienteMapper converter;

    public List<ListaOcioCliente> getListas(Integer id){
        return repository.findByClienteId(id);
    }

}
