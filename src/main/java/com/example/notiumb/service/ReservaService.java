package com.example.notiumb.service;

import com.example.notiumb.converter.IReservaMapper;
import com.example.notiumb.dto.ReservaDTO;
import com.example.notiumb.repository.IReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private IReservaRepository IReservaRepository;

    @Autowired
    private IReservaMapper reservaMapper;

    public List<ReservaDTO> listarReserva(){
        return reservaMapper.toDTO(IReservaRepository.findAll());
    }
}
