package com.example.notiumb.service;

import com.example.notiumb.converter.IEventoMapper;
import com.example.notiumb.dto.EntradaOcioDTO;
import com.example.notiumb.dto.EventoDTO;
import com.example.notiumb.dto.ListaOcioDTO;
import com.example.notiumb.dto.ReservadoOcioDTO;
import com.example.notiumb.model.*;
import com.example.notiumb.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {
    @Autowired
    private IEventoRepository eventoRepository;
    @Autowired
    private IEventoMapper eventoMapper;
    @Autowired
    private IOcioNocturnoRepository ocioNocturnoRepository;
    @Autowired
    private IEntradaOcioRepository entradaOcioRepository;
    @Autowired
    private IListaOcioRepository listaOcioRepository;
    @Autowired
    private IReservadoOcioRepository reservadoOcioRepository;

    public List<EventoDTO> getAll() {
        return eventoMapper.toDTO(eventoRepository.findAll());
    }

    public EventoDTO crearEvento(EventoDTO eventoDTO, EntradaOcioDTO entradaOcioDTO, ReservadoOcioDTO reservadoOcioDTO, ListaOcioDTO listaOcioDTO){
        OcioNocturno ocioNocturno = ocioNocturnoRepository.findById(eventoDTO.getOcioNocturnoDTO().getId()).orElse(null);

        if (ocioNocturno != null && entradaOcioDTO != null && reservadoOcioDTO != null && listaOcioDTO != null){
            Evento evento = crearEventoPersonalizado(eventoDTO, ocioNocturno);
            EntradaOcio entradaOcio = crearEntradasDeEvento(evento, entradaOcioDTO);
            ReservadoOcio reservadoOcio = crearReservadoDeEvento(evento, reservadoOcioDTO);
            ListaOcio listaOcio = crearListasDeEvento(evento, listaOcioDTO);
            eventoRepository.save(evento);
            entradaOcioRepository.save(entradaOcio);
            reservadoOcioRepository.save(reservadoOcio);
            listaOcioRepository.save(listaOcio);
            return eventoMapper.toDTO(evento);
        }else {
            return null;
        }
    }

    private static Evento crearEventoPersonalizado(EventoDTO eventoDTO, OcioNocturno ocioNocturno) {
        Evento eventoACrear = new Evento();
        eventoACrear.setNombre(eventoDTO.getNombre());
        eventoACrear.setDescripcion(eventoDTO.getDescripcion());
        eventoACrear.setTematica(eventoDTO.getTematica());
        eventoACrear.setFecha(eventoDTO.getFecha());
        eventoACrear.setCodigoVestimentaOcio(eventoDTO.getCodigoVestimentaOcio());
        eventoACrear.setEdadMinimaOcio(eventoDTO.getEdadMinimaOcio());
        eventoACrear.setAforo(eventoDTO.getAforo());
        eventoACrear.setOcioNocturno(ocioNocturno);
        return eventoACrear;
    }
    private static EntradaOcio crearEntradasDeEvento(Evento evento, EntradaOcioDTO entradaOcioDTO) {
        EntradaOcio entradaOcioACrear = new EntradaOcio();
        entradaOcioACrear.setEvento(evento);
        entradaOcioACrear.setPrecio(entradaOcioDTO.getPrecio());
        entradaOcioACrear.setTotalEntradas(entradaOcioDTO.getTotalEntradas());
        return entradaOcioACrear;
    }

    private static ReservadoOcio crearReservadoDeEvento(Evento evento, ReservadoOcioDTO reservadoOcioDTO){
        ReservadoOcio reservadoOcioACrear = new ReservadoOcio();
        return reservadoOcioACrear;
    }

    private static ListaOcio crearListasDeEvento(Evento evento, ListaOcioDTO listaOcioDTO){
        ListaOcio listaOcioACrear = new ListaOcio();
        return listaOcioACrear;
    }
}
