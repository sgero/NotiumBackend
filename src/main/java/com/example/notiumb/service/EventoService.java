package com.example.notiumb.service;

import com.example.notiumb.converter.IEventoMapper;
import com.example.notiumb.dto.EventoDTO;
import com.example.notiumb.repository.IEventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {
    @Autowired
    private IEventoRepository eventoRepository;

    @Autowired
    private IEventoMapper eventoMapper;
    public List<EventoDTO> getAll() {
        return eventoMapper.toDTO(eventoRepository.findAll());
    }
}
