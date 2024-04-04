package com.example.notiumb.service;

import com.example.notiumb.converter.IEventoMapper;
import com.example.notiumb.converter.IListaOcioMapper;
import com.example.notiumb.converter.IOcioNocturnoMapper;
import com.example.notiumb.converter.IRppMapper;
import com.example.notiumb.dto.EntradaOcioDTO;
import com.example.notiumb.dto.EventoDTO;
import com.example.notiumb.dto.ListaOcioDTO;
import com.example.notiumb.dto.ReservadoOcioDTO;
import com.example.notiumb.model.*;
import com.example.notiumb.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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
    @Autowired
    private IRppMapper rppMapper;
    @Autowired
    private IOcioNocturnoMapper ocioNocturnoMapper;
    @Autowired
    private IRppRepository rppRepository;

    public List<EventoDTO> getAll() {
        return eventoMapper.toDTO(eventoRepository.findAll());
    }

    public EventoDTO crearEvento(EventoDTO eventoDTO, EntradaOcioDTO entradaOcioDTO, ReservadoOcioDTO reservadoOcioDTO, List<ListaOcioDTO> listaOcioDTO){
        OcioNocturno ocioNocturno = ocioNocturnoRepository.findById(eventoDTO.getOcioNocturnoDTO().getId()).orElse(null);

        if (ocioNocturno != null && entradaOcioDTO != null && reservadoOcioDTO != null && listaOcioDTO != null){
            Evento evento = crearEventoPersonalizado(eventoDTO, ocioNocturno);
            EntradaOcio entradaOcio = crearEntradasDeEvento(evento, entradaOcioDTO);
            ReservadoOcio reservadoOcio = crearReservadoDeEvento(evento, reservadoOcioDTO);
            List<ListaOcio> listaOcio = crearListasDeEvento(evento, listaOcioDTO);
            Double invitacionesLista = totalInvitacionesLista(listaOcio);
            if (evento != null && evento.getAforo() >=
                    (entradaOcio.getTotalEntradas() + (reservadoOcio.getReservadosDisponibles() * reservadoOcio.getPersonasMaximasPorReservado()) + invitacionesLista)){
                eventoRepository.save(evento);
                entradaOcioRepository.save(entradaOcio);
                reservadoOcioRepository.save(reservadoOcio);
                listaOcioRepository.saveAll(listaOcio);
                return eventoMapper.toDTO(evento);
            }else {
                return null;
            }
        }else {
            return null;
        }
    }

    private Evento crearEventoPersonalizado(EventoDTO eventoDTO, OcioNocturno ocioNocturno) {
        Evento eventoACrear = new Evento();
        eventoACrear.setNombre(eventoDTO.getNombre());
        eventoACrear.setDescripcion(eventoDTO.getDescripcion());
        eventoACrear.setTematica(eventoDTO.getTematica());
        eventoACrear.setFecha(eventoDTO.getFecha());
        eventoACrear.setCodigoVestimentaOcio(eventoDTO.getCodigoVestimentaOcio());
        eventoACrear.setEdadMinimaOcio(eventoDTO.getEdadMinimaOcio());
        eventoACrear.setAforo(eventoDTO.getAforo());
        eventoACrear.setOcioNocturno(ocioNocturno);
        if (eventoACrear.getAforo() > ocioNocturno.getAforo()){
            return null;
        }
        return eventoACrear;
    }
    private EntradaOcio crearEntradasDeEvento(Evento evento, EntradaOcioDTO entradaOcioDTO) {
        EntradaOcio entradaOcioACrear = new EntradaOcio();
        entradaOcioACrear.setEvento(evento);
        entradaOcioACrear.setPrecio(entradaOcioDTO.getPrecio());
        entradaOcioACrear.setTotalEntradas(entradaOcioDTO.getTotalEntradas());
        return entradaOcioACrear;
    }

    private ReservadoOcio crearReservadoDeEvento(Evento evento, ReservadoOcioDTO reservadoOcioDTO){
        ReservadoOcio reservadoOcioACrear = new ReservadoOcio();
        reservadoOcioACrear.setReservadosDisponibles(reservadoOcioDTO.getReservadosDisponibles());
        reservadoOcioACrear.setPersonasMaximasPorReservado(reservadoOcioDTO.getPersonasMaximasPorReservado());
        reservadoOcioACrear.setPrecio(reservadoOcioDTO.getPrecio());
        reservadoOcioACrear.setEvento(evento);
        return reservadoOcioACrear;
    }

    private List<ListaOcio> crearListasDeEvento(Evento evento, List<ListaOcioDTO> listaOcioDTO){
        List<ListaOcio> listasOcio = new ArrayList<>();
        for (ListaOcioDTO l : listaOcioDTO) {
            OcioNocturno ocioNocturno = ocioNocturnoRepository.findById(evento.getOcioNocturno().getId()).orElse(null);
            Rpp rpp = rppRepository.findById(l.getRppDTO().getId()).orElse(null);
            assert ocioNocturno != null;
            assert rpp != null;
            if (rpp.getOcioNocturno() == ocioNocturno){
                ListaOcio listaOcioACrear = new ListaOcio();
                listaOcioACrear.setPrecio(l.getPrecio());
                listaOcioACrear.setTotal_invitaciones(l.getTotal_invitaciones());
                listaOcioACrear.setEvento(evento);
                listaOcioACrear.setRpp(rppMapper.toEntity(l.getRppDTO()));
                listasOcio.add(listaOcioACrear);
            }
        }
        return listasOcio;
    }

    private Double totalInvitacionesLista(List<ListaOcio> listaOcio){
        Double total = 0.00;
        for (ListaOcio l : listaOcio){
            total += l.getTotal_invitaciones();
        }
        return total;
    }
}
