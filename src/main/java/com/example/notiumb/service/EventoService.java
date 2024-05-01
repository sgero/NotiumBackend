package com.example.notiumb.service;

import com.example.notiumb.converter.IEventoMapper;
import com.example.notiumb.converter.IListaOcioMapper;
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
import jakarta.transaction.Transactional;
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
    @Autowired
    private IListaOcioMapper listaOcioMapper;
    @Autowired
    private ListaOcioService listaOcioService;

    public RespuestaDTO getAll() {
        RespuestaDTO respuestaDTO = new RespuestaDTO();
        UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_200_EVENTO_LISTAR, eventoMapper.toDTO(eventoRepository.findAll()));
        return respuestaDTO;
    }
    public RespuestaDTO getActivos() {
        RespuestaDTO respuestaDTO = new RespuestaDTO();
        UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_200_EVENTO_LISTAR, eventoMapper.toDTO(eventoRepository.findByActivoIsTrue()));
        return respuestaDTO;
    }

    @Transactional
    public RespuestaDTO guardarEvento(EventoDTO eventoDTO, EntradaOcioDTO entradaOcioDTO, ReservadoOcioDTO reservadoOcioDTO, List<ListaOcioDTO> listaOcioDTO){
        RespuestaDTO respuestaDTO = new RespuestaDTO();
        try{
            OcioNocturno ocioNocturno = ocioNocturnoRepository.findById(eventoDTO.getOcioNocturnoDTO().getId()).orElse(null);

            // Verificamos si es crear o editar
            if (eventoDTO.getId() == null){
                if (ocioNocturno != null && entradaOcioDTO != null && reservadoOcioDTO != null && listaOcioDTO != null){
                    Evento evento = crearEventoPersonalizado(eventoDTO, ocioNocturno);
                    EntradaOcio entradaOcio = crearEntradasDeEvento(evento, entradaOcioDTO);
                    ReservadoOcio reservadoOcio = crearReservadoDeEvento(evento, reservadoOcioDTO);
                    List<ListaOcio> listaOcio = crearListasDeEvento(evento, listaOcioDTO);
                    Double invitacionesLista = totalInvitacionesLista(listaOcio);
                    if (evento.getAforo() >=
                            entradaOcio.getTotalEntradas() + reservadoOcio.getReservadosDisponibles() * reservadoOcio.getPersonasMaximasPorReservado() + invitacionesLista){
                        eventoRepository.save(evento);
                        entradaOcioRepository.save(entradaOcio);
                        reservadoOcioRepository.save(reservadoOcio);
                        listaOcioRepository.saveAll(listaOcio);
                        EventoDTO eventoGuardado = eventoMapper.toDTO(evento);
                        UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_200_EVENTO_UNICO_CREADO, eventoGuardado);
                    }else {
                        UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_ERROR_2);
                    }
                }else {
                    UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_ERROR_1);
                }
            }else {
                Evento eventoEditar = eventoRepository.findById(eventoDTO.getId()).orElse(null);
                EntradaOcio entradaOcioEditar = entradaOcioRepository.findById(entradaOcioDTO.getId()).orElse(null);
                ReservadoOcio reservadoOcioEditar = reservadoOcioRepository.findById(reservadoOcioDTO.getId()).orElse(null);
                if (eventoEditar != null && entradaOcioEditar != null && reservadoOcioEditar != null){
                    setearEvento(eventoDTO, ocioNocturno, eventoEditar);
                    setearEntrada(eventoEditar, entradaOcioDTO, entradaOcioEditar);
                    setearReservado(eventoEditar, reservadoOcioDTO, reservadoOcioEditar);
                    List<ListaOcio> listadoOcio = new ArrayList<>();
                    for (ListaOcioDTO l : listaOcioDTO){
                        ListaOcio l2 = listaOcioRepository.findById(l.getId()).orElse(null);
                        l.setEventoDTO(eventoDTO);
                        if (l2 != null) {
                            setearLista(eventoEditar, l2, l);
                            listadoOcio.add(listaOcioMapper.toEntity(l));
                        }
                    }
                    Double invitacionesLista = totalInvitacionesLista(listadoOcio);
                    if (eventoEditar.getAforo() >=
                            entradaOcioEditar.getTotalEntradas() + reservadoOcioEditar.getReservadosDisponibles() * reservadoOcioEditar.getPersonasMaximasPorReservado() + invitacionesLista){
                        eventoRepository.save(eventoEditar);
                        entradaOcioRepository.save(entradaOcioEditar);
                        reservadoOcioRepository.save(reservadoOcioEditar);
                        listaOcioRepository.saveAll(listadoOcio);
                        EventoDTO eventoGuardado = eventoMapper.toDTO(eventoEditar);
                        UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_200_EVENTO_EDITADO, eventoGuardado);
                    }else {
                        UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_ERROR_2);
                    }
                }
            }
        }catch (Exception e){
            ULogger.error(e);
            UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_ERROR_500);
        }
        return respuestaDTO;
    }

    private Evento crearEventoPersonalizado(EventoDTO eventoDTO, OcioNocturno ocioNocturno) {
        Evento eventoACrear = new Evento();
        setearEvento(eventoDTO, ocioNocturno, eventoACrear);
        return eventoACrear;
    }

    private static void setearEvento(EventoDTO eventoDTO, OcioNocturno ocioNocturno, Evento eventoGuardar) {
        eventoGuardar.setNombre(eventoDTO.getNombre());
        eventoGuardar.setDescripcion(eventoDTO.getDescripcion());
        eventoGuardar.setTematica(eventoDTO.getTematica());
        eventoGuardar.setFecha(eventoDTO.getFecha());
        eventoGuardar.setCodigoVestimentaOcio(eventoDTO.getCodigoVestimentaOcio());
        eventoGuardar.setEdadMinimaOcio(eventoDTO.getEdadMinimaOcio());
        eventoGuardar.setAforo(eventoDTO.getAforo());
        eventoGuardar.setOcioNocturno(ocioNocturno);
    }

    private EntradaOcio crearEntradasDeEvento(Evento evento, EntradaOcioDTO entradaOcioDTO) {
        EntradaOcio entradaOcioACrear = new EntradaOcio();
        setearEntrada(evento, entradaOcioDTO, entradaOcioACrear);
        return entradaOcioACrear;
    }

    private static void setearEntrada(Evento evento, EntradaOcioDTO entradaOcioDTO, EntradaOcio entradaOcioGuardar) {
        entradaOcioGuardar.setEvento(evento);
        entradaOcioGuardar.setPrecio(entradaOcioDTO.getPrecio());
        entradaOcioGuardar.setTotalEntradas(entradaOcioDTO.getTotalEntradas());
    }

    private ReservadoOcio crearReservadoDeEvento(Evento evento, ReservadoOcioDTO reservadoOcioDTO){
        ReservadoOcio reservadoOcioACrear = new ReservadoOcio();
        setearReservado(evento, reservadoOcioDTO, reservadoOcioACrear);
        return reservadoOcioACrear;
    }

    private static void setearReservado(Evento evento, ReservadoOcioDTO reservadoOcioDTO, ReservadoOcio reservadoOcioGuardar) {
        reservadoOcioGuardar.setReservadosDisponibles(reservadoOcioDTO.getReservadosDisponibles());
        reservadoOcioGuardar.setPersonasMaximasPorReservado(reservadoOcioDTO.getPersonasMaximasPorReservado());
        reservadoOcioGuardar.setPrecio(reservadoOcioDTO.getPrecio());
        reservadoOcioGuardar.setEvento(evento);
    }

    private List<ListaOcio> crearListasDeEvento(Evento evento, List<ListaOcioDTO> listaOcioDTO){
        List<ListaOcio> listasOcio = new ArrayList<>();
        OcioNocturno ocioNocturno = ocioNocturnoRepository.findById(evento.getOcioNocturno().getId()).orElse(null);
        for (ListaOcioDTO l : listaOcioDTO) {
            Rpp rpp = rppRepository.findById(l.getRppDTO().getId()).orElse(null);
            assert rpp != null;
            if (rpp.getOcioNocturno() == ocioNocturno){
                ListaOcio listaOcioACrear = new ListaOcio();
                setearLista(evento, listaOcioACrear, l);
                listasOcio.add(listaOcioACrear);
            }
        }
        return listasOcio;
    }

    private void setearLista(Evento evento,  ListaOcio listaOcioGuardar, ListaOcioDTO indiceLista) {
        listaOcioGuardar.setPrecio(indiceLista.getPrecio());
        listaOcioGuardar.setTotal_invitaciones(indiceLista.getTotal_invitaciones());
        listaOcioGuardar.setEvento(evento);
        listaOcioGuardar.setRpp(rppMapper.toEntity(indiceLista.getRppDTO()));
    }

    private Double totalInvitacionesLista(List<ListaOcio> listaOcio){
        Double total = 0.00;
        for (ListaOcio l : listaOcio){
            total += l.getTotal_invitaciones();
        }
        return total;
    }

    @Transactional
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
                        if (evento.getAforo() >= totalAforoEvento){
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

    @Transactional
    public RespuestaDTO eliminarEvento(Integer id) {
        RespuestaDTO respuestaDTO = new RespuestaDTO();
        Evento eventoAEliminar = eventoRepository.findById(id).orElse(null);
        try{
            if (eventoAEliminar != null){
                EntradaOcio entradaOcio = entradaOcioRepository.findEntradaOcioByEventoIdAndActivoIsTrue(eventoAEliminar.getId());
                ReservadoOcio reservadoOcio = reservadoOcioRepository.findReservadoOcioByEventoIdAndActivoIsTrue(eventoAEliminar.getId());
                List<ListaOcio> ocioList = listaOcioMapper.toEntity(listaOcioService.getAllByEventoId(eventoAEliminar.getId()));
                List<ListaOcio> listaEliminar = new ArrayList<>();
                if (!ocioList.isEmpty()){
                    for (ListaOcio l : ocioList){
                        l.setActivo(false);
                        listaEliminar.add(l);
                    }
                }
                eventoAEliminar.setActivo(false);
                if (entradaOcio != null && reservadoOcio != null){
                    entradaOcio.setActivo(false);
                    reservadoOcio.setActivo(false);
                    entradaOcioRepository.save(entradaOcio);
                    reservadoOcioRepository.save(reservadoOcio);
                }
                eventoRepository.save(eventoAEliminar);
                listaOcioRepository.saveAll(listaEliminar);
                UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_200_EVENTO_ELIMINADO);
            }
        }catch (Exception e){
            ULogger.error(e);
            UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_ERROR_500);
        }
        return respuestaDTO;
    }

}
