package com.example.notiumb.service;

import com.example.notiumb.converter.IEventoMapper;
import com.example.notiumb.converter.IRppMapper;
import com.example.notiumb.dto.EntradaOcioDTO;
import com.example.notiumb.dto.EventoDTO;
import com.example.notiumb.dto.ListaOcioDTO;
import com.example.notiumb.dto.ReservadoOcioDTO;
import com.example.notiumb.model.*;
import com.example.notiumb.model.enums.DiasARepetirCicloEventoOcio;
import com.example.notiumb.model.enums.RepetirCicloEventoOcio;
import com.example.notiumb.repository.*;
import com.example.notiumb.utilidades.MapaCodigoRespuestaAPI;
import com.example.notiumb.utilidades.RespuestaDTO;
import com.example.notiumb.utilidades.ULogger;
import com.example.notiumb.utilidades.UtilidadesAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @Autowired
    private IRppMapper rppMapper;
    @Autowired
    private IRppRepository rppRepository;

    public RespuestaDTO getAll() {
        RespuestaDTO respuestaDTO = new RespuestaDTO();
        UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_200_EVENTO_LISTAR, eventoMapper.toDTO(eventoRepository.findAll()));
        return respuestaDTO;
    }

    public RespuestaDTO crearEventoUnico(EventoDTO eventoDTO, EntradaOcioDTO entradaOcioDTO, ReservadoOcioDTO reservadoOcioDTO, List<ListaOcioDTO> listaOcioDTO){
        RespuestaDTO respuestaDTO = new RespuestaDTO();
        try{
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
                    EventoDTO eventoCreado = eventoMapper.toDTO(evento);
                    UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_200_EVENTO_UNICO_CREADO, eventoCreado);
                }else {
                    UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_ERROR_2);
                }
            }else {
                UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_ERROR_1);
            }
        }catch (Exception e){
            ULogger.error(e);
            UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_ERROR_500);
        }
        return respuestaDTO;
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

    public RespuestaDTO crearEventoCiclico(EventoDTO eventoDTO,
                                           EntradaOcioDTO entradaOcioDTO,
                                           ReservadoOcioDTO reservadoOcioDTO,
                                           List<ListaOcioDTO> listaOcioDTO,
                                           RepetirCicloEventoOcio repetirCicloEventoOcio,
                                           List<DiasARepetirCicloEventoOcio> diasARepetirCicloEventoOcioList) {
        RespuestaDTO respuestaDTO = new RespuestaDTO();
        try {
            if (repetirCicloEventoOcio != null && diasARepetirCicloEventoOcioList != null){
                Timestamp fechaInicio = eventoDTO.getFecha();
                Timestamp fechaFin = getFechaFinEventoCiclico(eventoDTO, repetirCicloEventoOcio);

                OcioNocturno ocioNocturno = ocioNocturnoRepository.findById(eventoDTO.getOcioNocturnoDTO().getId()).orElse(null);

                if (ocioNocturno != null && entradaOcioDTO != null && reservadoOcioDTO != null && listaOcioDTO != null){

                    List<Timestamp> fechasACrearEvento = obtenerFechasACrearEventoEntre(fechaInicio, fechaFin, diasARepetirCicloEventoOcioList);

                    for (Timestamp f : fechasACrearEvento){
                        Evento evento = crearEventoPersonalizadoCiclico(eventoDTO, ocioNocturno, f);
                        EntradaOcio entradaOcio = crearEntradasDeEvento(evento, entradaOcioDTO);
                        ReservadoOcio reservadoOcio = crearReservadoDeEvento(evento, reservadoOcioDTO);
                        List<ListaOcio> listaOcio = crearListasDeEvento(evento, listaOcioDTO);
                        Double invitacionesLista = totalInvitacionesLista(listaOcio);
                        Integer totalAforoEvento = (int) (entradaOcio.getTotalEntradas()
                                + (reservadoOcio.getReservadosDisponibles() * reservadoOcio.getPersonasMaximasPorReservado())
                                + invitacionesLista);
                        if (evento != null && evento.getAforo() >= totalAforoEvento){
                            eventoRepository.save(evento);
                            entradaOcioRepository.save(entradaOcio);
                            reservadoOcioRepository.save(reservadoOcio);
                            listaOcioRepository.saveAll(listaOcio);
                            EventoDTO eventoCreado = eventoMapper.toDTO(evento);
                            UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_200_EVENTO_UNICO_CREADO, eventoCreado);
                        }else {
                            UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_ERROR_2);
                        }
                    }
                }else {
                    UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_ERROR_1);
                }
            }else {
                UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_ERROR_1);
            }

        }catch (Exception e){
            ULogger.error(e);
            UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_ERROR_500);
        }
        return respuestaDTO;
    }

    private Timestamp getFechaFinEventoCiclico(EventoDTO eventoDTO, RepetirCicloEventoOcio repetirCicloEventoOcio){
        Timestamp fechaInicio = eventoDTO.getFecha();
        LocalDateTime fechaInicioLocalDateTime = fechaInicio.toLocalDateTime();
        Timestamp fechaFin = null;
        fechaFin = getTimestampFinal(repetirCicloEventoOcio, fechaInicioLocalDateTime, fechaFin);
        return fechaFin;
    }

    private Timestamp getTimestampFinal(RepetirCicloEventoOcio repetirCicloEventoOcio, LocalDateTime fechaInicioLocalDateTime, Timestamp fechaFin) {
        if (repetirCicloEventoOcio.equals(RepetirCicloEventoOcio.UNA_SEMANA)){
            LocalDateTime fechaUnaSemanaDespues = fechaInicioLocalDateTime.plusWeeks(1);
            fechaFin = Timestamp.valueOf(fechaUnaSemanaDespues);
        }else if (repetirCicloEventoOcio.equals(RepetirCicloEventoOcio.UN_MES)){
            LocalDateTime fechaUnaSemanaDespues = fechaInicioLocalDateTime.plusMonths(1);
            fechaFin = Timestamp.valueOf(fechaUnaSemanaDespues);
        }else if (repetirCicloEventoOcio.equals(RepetirCicloEventoOcio.TRES_MESES)){
            LocalDateTime fechaUnaSemanaDespues = fechaInicioLocalDateTime.plusMonths(3);
            fechaFin = Timestamp.valueOf(fechaUnaSemanaDespues);
        }else if (repetirCicloEventoOcio.equals(RepetirCicloEventoOcio.SEIS_MESES)){
            LocalDateTime fechaUnaSemanaDespues = fechaInicioLocalDateTime.plusMonths(6);
            fechaFin = Timestamp.valueOf(fechaUnaSemanaDespues);
        }
        return fechaFin;
    }

    private List<Timestamp> obtenerFechasACrearEventoEntre(Timestamp fechaInicio, Timestamp fechaFin, List<DiasARepetirCicloEventoOcio> diasARepetirCicloEventoOcioList) {
        List<Timestamp> fechas = new ArrayList<>();
        LocalDateTime fechaAEnviar = fechaInicio.toLocalDateTime();
        LocalDateTime fin = fechaFin.toLocalDateTime();
        while (!fechaAEnviar.isAfter(fin)){
            for (DiasARepetirCicloEventoOcio d : diasARepetirCicloEventoOcioList){
                if (d.name().equals(fechaAEnviar.getDayOfWeek().name())){
                    fechas.add(Timestamp.valueOf(fechaAEnviar));
                }
            }
            fechaAEnviar = fechaAEnviar.plusDays(1);
        }

        return fechas;
    }

    private Evento crearEventoPersonalizadoCiclico(EventoDTO eventoDTO, OcioNocturno ocioNocturno, Timestamp fecha) {
        Evento eventoACrear = new Evento();
        eventoACrear.setNombre(eventoDTO.getNombre());
        eventoACrear.setDescripcion(eventoDTO.getDescripcion());
        eventoACrear.setTematica(eventoDTO.getTematica());
        eventoACrear.setFecha(fecha);
        eventoACrear.setCodigoVestimentaOcio(eventoDTO.getCodigoVestimentaOcio());
        eventoACrear.setEdadMinimaOcio(eventoDTO.getEdadMinimaOcio());
        eventoACrear.setAforo(eventoDTO.getAforo());
        eventoACrear.setOcioNocturno(ocioNocturno);
        if (eventoACrear.getAforo() > ocioNocturno.getAforo()){
            eventoACrear = null;
            return eventoACrear;
        }
        return eventoACrear;
    }

    public RespuestaDTO eliminarEvento(Integer id) {
        RespuestaDTO respuestaDTO = new RespuestaDTO();
        Evento eventoAEliminar = eventoRepository.findById(id).orElse(null);
        try{
            if (eventoAEliminar != null){
                eventoAEliminar.setActivo(false);
                eventoRepository.save(eventoAEliminar);
                UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_200_EVENTO_ELIMINADO);
            }
        }catch (Exception e){
            ULogger.error(e);
            UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_ERROR_500);
        }
        return respuestaDTO;
    }
}
