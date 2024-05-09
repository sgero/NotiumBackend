package com.example.notiumb.service;

import com.example.notiumb.converter.IEventoMapper;
import com.example.notiumb.converter.IReservadoOcioMapper;
import com.example.notiumb.dto.ReservadoOcioDTO;
import com.example.notiumb.model.ReservadoOcio;
import com.example.notiumb.repository.IEventoRepository;
import com.example.notiumb.repository.IReservadoOcioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<ReservadoOcioDTO> getAll(){
        return converter.toDTO(repository.findAllByActivoIsTrue());
    }

    public ReservadoOcioDTO getById(@Param("id") Integer id){
        return converter.toDTO(repository.findByIdAndActivoIsTrue(id).orElse(null));
    }

    public void delete(@Param("id") Integer id){
        ReservadoOcio deleteReservado = repository.findByIdAndActivoIsTrue(id).orElse(null);
        if (deleteReservado!=null){
            deleteReservado.setActivo(false);
            repository.save(deleteReservado);
        }
    }





}
