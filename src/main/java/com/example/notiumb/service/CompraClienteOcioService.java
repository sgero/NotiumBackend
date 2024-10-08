package com.example.notiumb.service;

import com.example.notiumb.converter.IDatosCompradorMapper;
import com.example.notiumb.converter.IEntradaOcioClienteMapper;
import com.example.notiumb.converter.IListaOcioClienteMapper;
import com.example.notiumb.converter.IReservadoOcioClienteMapper;
import com.example.notiumb.dto.*;
import com.example.notiumb.model.*;
import com.example.notiumb.repository.*;
import com.example.notiumb.utilidades.MapaCodigoRespuestaAPI;
import com.example.notiumb.utilidades.RespuestaDTO;
import com.example.notiumb.utilidades.UtilidadesAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CompraClienteOcioService {
    @Autowired
    private IEventoRepository eventoRepository;
    @Autowired
    private IEntradaOcioClienteRepository entradaOcioClienteRepository;
    @Autowired
    private IReservadoOcioClienteRepository reservadoOcioClienteRepository;
    @Autowired
    private IListaOcioClienteRepository listaOcioClienteRepository;
    @Autowired
    private IEntradaOcioRepository entradaOcioRepository;
    @Autowired
    private IReservadoOcioRepository reservadoOcioRepository;
    @Autowired
    private IListaOcioRepository listaOcioRepository;
    @Autowired
    private IClienteRepository clienteRepository;
    @Autowired
    private IDatosCompradorRepository datosCompradorRepository;
    @Autowired
    private IPromocionRepository promocionRepository;

    @Autowired
    private IEntradaOcioClienteMapper entradaOcioClienteMapper;
    @Autowired
    private IListaOcioClienteMapper listaOcioClienteMapper;
    @Autowired
    private IReservadoOcioClienteMapper reservadoOcioClienteMapper;
    @Autowired
    private IDatosCompradorMapper datosCompradorMapper;

    public boolean aforoNoCompleto(Evento evento, List<EntradaOcioCliente> entradasOcioCliente, ReservadoOcioCliente reservadoOcioCliente, List<ListaOcioCliente> listaOcioCliente) {
        Integer aforoEvento = evento.getAforo();

        EntradaOcio entradaOcio = entradaOcioRepository.findEntradaOcioByEventoIdAndActivoIsTrue(evento.getId());
        ReservadoOcio reservadoOcio = reservadoOcioRepository.findReservadoOcioByEventoIdAndActivoIsTrue(evento.getId());
        List<ListaOcio> listaOcio = listaOcioRepository.findAllByEventoId(evento.getId());

        Integer entradasVendidas = entradaOcioClienteRepository.cantidadEntradasVendidas(entradaOcio.getId());
        Integer reservadosVendidos = reservadoOcioClienteRepository.cantidadReservadosVendidos(reservadoOcio.getId());
        Integer personasTotalesReservadosVendidos = reservadoOcioClienteRepository.cantidadPersonas(reservadoOcio.getId());
        Integer invitacionesTotalesLista = listaOcio.stream().mapToInt(ListaOcio::getTotal_invitaciones).sum();
        Integer clientesApuntadosALista = listaOcioClienteRepository.cantidadClientesTotales(evento.getId());

        if (!CollectionUtils.isEmpty(entradasOcioCliente)) {
            return entradasVendidas < entradaOcio.getTotalEntradas()
                    && (entradasOcioCliente.size() + entradasVendidas) < entradaOcio.getTotalEntradas()
                    && (entradasOcioCliente.size() + entradasVendidas) < aforoEvento;
        } else if (reservadoOcioCliente != null && reservadoOcioCliente.getCantidad_personas() != null) {
            return reservadoOcioCliente.getCantidad_personas() <= reservadoOcio.getPersonasMaximasPorReservado()
                    && reservadosVendidos < reservadoOcio.getReservadosDisponibles()
                    && (reservadoOcioCliente.getCantidad_personas() + (personasTotalesReservadosVendidos != null ? personasTotalesReservadosVendidos : 0)) < aforoEvento;
        } else if (!CollectionUtils.isEmpty(listaOcioCliente)) {
            return clientesApuntadosALista < invitacionesTotalesLista
                    && (listaOcioCliente.size() + clientesApuntadosALista) < invitacionesTotalesLista
                    && (listaOcioCliente.size() + clientesApuntadosALista) < aforoEvento;
        }
        return false;
    }

    public RespuestaDTO comprarEntradaNormal(Integer idEvento, Integer idCliente, Integer entradaOcioALaVenta, List<EntradaOcioClienteDTO> entradasOcioClienteLista) {
        Cliente cliente = clienteRepository.findByIdAndActivoIsTrue(idCliente).orElse(null);
        Evento evento = eventoRepository.findEventoByIdAndActivoIsTrue(idEvento);
        EntradaOcio entradaOcio = entradaOcioRepository.findByIdAndActivoIsTrue(entradaOcioALaVenta);
        RespuestaDTO respuestaDTO = new RespuestaDTO();
        Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
        if (evento != null && cliente != null && entradaOcio != null && !CollectionUtils.isEmpty(entradasOcioClienteLista)) {
            List<EntradaOcioCliente> listaEntradasCompradas = entradaOcioClienteMapper.toEntity(entradasOcioClienteLista);
            if (aforoNoCompleto(evento, listaEntradasCompradas, null, null)) {
                List<EntradaOcioCliente> entradasCompradas = new ArrayList<>();
                setearEntradasGenerales(listaEntradasCompradas, fechaActual, cliente, entradaOcio, entradasCompradas);
                if (!CollectionUtils.isEmpty(entradasCompradas) && entradasCompradas.size() == entradasOcioClienteLista.size()) {
                    entradaOcioClienteRepository.saveAll(entradasCompradas);
                    UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_200_ENTRADAS_COMPRADAS, entradaOcioClienteMapper.toDTO(entradasCompradas));
                }
            } else {
                UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_ERROR_AFORO);
            }
        } else {
            UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_ERROR_EVENTO);
        }
        return respuestaDTO;
    }

    private void setearEntradasGenerales(List<EntradaOcioCliente> listaEntradasCompradas, Timestamp fechaActual, Cliente cliente, EntradaOcio entradaOcio, List<EntradaOcioCliente> entradasCompradas) {
        listaEntradasCompradas.forEach(l -> {
            if (l.getDatosComprador() != null && l.getDatosComprador().getReservadoOcioCliente() == null) {
                if (l.getPromocion() != null && l.getPromocion().getId() != null) {
                    Promocion promocion = promocionRepository.findById(l.getPromocion().getId()).orElse(null);
                    if (promocion != null) {
                        EntradaOcioCliente entradaComprada = EntradaOcioCliente.builder()
                                .codigo("EOC")
                                .fechaCompra(fechaActual)
                                .cliente(cliente)
                                .entradaOcio(entradaOcio)
                                .datosComprador(l.getDatosComprador())
                                .promocion(promocion)
                                .build();
                        entradasCompradas.add(entradaComprada);
                    }
                } else {
                    EntradaOcioCliente entradaComprada = EntradaOcioCliente.builder()
                            .codigo("EOC")
                            .fechaCompra(fechaActual)
                            .cliente(cliente)
                            .entradaOcio(entradaOcio)
                            .datosComprador(l.getDatosComprador())
                            .build();
                    entradasCompradas.add(entradaComprada);
                }
            }
        });
    }

    public RespuestaDTO comprarReservado(Integer idEvento, Integer idCliente, Integer idReservadoOcio, ReservadoOcioClienteDTO reservadoOcioClienteDTO, List<DatosCompradorDTO> datosCompradorDTOS) {
        RespuestaDTO respuestaDTO = new RespuestaDTO();
        Cliente cliente = clienteRepository.findByIdAndActivoIsTrue(idCliente).orElse(null);
        Evento evento = eventoRepository.findEventoByIdAndActivoIsTrue(idEvento);
        ReservadoOcio reservadoOcio = reservadoOcioRepository.findByIdAndActivoIsTrue(idReservadoOcio).orElse(null);
        Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
        if (evento != null && cliente != null && reservadoOcio != null && reservadoOcioClienteDTO != null) {
            ReservadoOcioCliente reservadoOcioCliente = reservadoOcioClienteMapper.toEntity(reservadoOcioClienteDTO);
            if (reservadoOcioCliente != null && aforoNoCompleto(evento, null, reservadoOcioCliente, null)) {
                if (!CollectionUtils.isEmpty(datosCompradorDTOS) && datosCompradorDTOS.size() == reservadoOcioCliente.getCantidad_personas()) {
                    Set<DatosComprador> datosCompradorSet = convertirDatosSet(datosCompradorDTOS);
                    if (!CollectionUtils.isEmpty(datosCompradorSet) && datosCompradorSet.size() == reservadoOcioCliente.getCantidad_personas()) {
                        setearDatosReservadoOcioCliente(reservadoOcioCliente, cliente, fechaActual, reservadoOcio, reservadoOcioClienteDTO, datosCompradorSet);
                        datosCompradorSet.forEach(d -> d.setReservadoOcioCliente(reservadoOcioCliente));
                        datosCompradorRepository.saveAll(datosCompradorSet);
                        reservadoOcioClienteRepository.save(reservadoOcioCliente);
                        ComprarReservadoDTO comprarReservadoDTO = ComprarReservadoDTO.builder()
                                .reservadoOcioClienteDTO(reservadoOcioClienteMapper.toDTO(reservadoOcioCliente))
                                .datosCompradorDTOS(datosCompradorMapper.toDTO(datosCompradorSet))
                                .build();
                        UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_200_ENTRADAS_COMPRADAS, comprarReservadoDTO);
                    }
                } else {
                    UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_ERROR_DATOS);
                }
            } else {
                UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_ERROR_AFORO);
            }
        } else {
            UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_ERROR_EVENTO);
        }

        return respuestaDTO;
    }

    private Set<DatosComprador> convertirDatosSet(List<DatosCompradorDTO> datosCompradorDTOS) {
        Set<DatosComprador> datosCompradorSet = new HashSet<>();
        if (!CollectionUtils.isEmpty(datosCompradorDTOS)) {
            List<DatosComprador> comprador = datosCompradorMapper.toEntity(datosCompradorDTOS);
            datosCompradorSet.addAll(comprador);
            return datosCompradorSet;
        }
        return null;
    }

    private void setearDatosReservadoOcioCliente(ReservadoOcioCliente reservadoOcioCliente, Cliente cliente, Timestamp fechaActual, ReservadoOcio reservadoOcio, ReservadoOcioClienteDTO reservadoOcioClienteDTO, Set<DatosComprador> datosCompradorSet) {
        reservadoOcioCliente.setCodigo("ROC");
        reservadoOcioCliente.setCliente(cliente);
        reservadoOcioCliente.setFecha_compra(fechaActual);
        reservadoOcioCliente.setReservadoOcio(reservadoOcio);
        if (reservadoOcioClienteDTO.getPromocionDTO() != null && reservadoOcioClienteDTO.getPromocionDTO().getId() != null) {
            Promocion promocion = promocionRepository.findById(reservadoOcioClienteDTO.getPromocionDTO().getId()).orElse(null);
            if (promocion != null) {
                reservadoOcioCliente.setPromocion(promocion);
            }
        }
    }


    public RespuestaDTO comprarEntradaLista(Integer idEvento, Integer idCliente, Integer idListaOcio, List<ListaOcioClienteDTO> listaOcioClienteDTOS) {
        Cliente cliente = clienteRepository.findByIdAndActivoIsTrue(idCliente).orElse(null);
        Evento evento = eventoRepository.findEventoByIdAndActivoIsTrue(idEvento);
        ListaOcio listaOcio = listaOcioRepository.findByIdAndActivoIsTrue(idListaOcio).orElse(null);
        RespuestaDTO respuestaDTO = new RespuestaDTO();
        Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
        if (evento != null && cliente != null && listaOcio != null && !CollectionUtils.isEmpty(listaOcioClienteDTOS)) {
            List<ListaOcioCliente> listaEntradasCompradas = listaOcioClienteMapper.toEntity(listaOcioClienteDTOS);
            if (aforoNoCompleto(evento, null, null, listaEntradasCompradas)) {
                List<ListaOcioCliente> entradasListaCompradas = new ArrayList<>();
                setearEntradasLista(listaEntradasCompradas, fechaActual, cliente, listaOcio, entradasListaCompradas);
                if (!CollectionUtils.isEmpty(entradasListaCompradas) && entradasListaCompradas.size() == listaOcioClienteDTOS.size()) {
                    listaOcioClienteRepository.saveAll(entradasListaCompradas);
                    UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_200_ENTRADAS_COMPRADAS, listaOcioClienteMapper.toDTO(entradasListaCompradas));
                }

            } else {
                UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_ERROR_AFORO);
            }
        } else {
            UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_ERROR_EVENTO);
        }
        return respuestaDTO;
    }

    private void setearEntradasLista(List<ListaOcioCliente> listaEntradasCompradas, Timestamp fechaActual, Cliente cliente, ListaOcio listaOcio, List<ListaOcioCliente> entradasListaCompradas) {
        listaEntradasCompradas.forEach(l -> {
            if (l.getDatosComprador() != null && l.getDatosComprador().getReservadoOcioCliente() == null) {
                if (l.getPromocion() != null && l.getPromocion().getId() != null) {
                    Promocion promocion = promocionRepository.findById(l.getPromocion().getId()).orElse(null);
                    if (promocion != null) {
                        ListaOcioCliente listaOcioCliente = ListaOcioCliente.builder()
                                .codigo("LOC")
                                .fecha(fechaActual)
                                .cliente(cliente)
                                .listaOcio(listaOcio)
                                .datosComprador(l.getDatosComprador())
                                .promocion(promocion)
                                .build();
                        entradasListaCompradas.add(listaOcioCliente);
                    }
                } else {
                    ListaOcioCliente listaOcioCliente = ListaOcioCliente.builder()
                            .codigo("LOC")
                            .fecha(fechaActual)
                            .cliente(cliente)
                            .listaOcio(listaOcio)
                            .datosComprador(l.getDatosComprador())
                            .build();
                    entradasListaCompradas.add(listaOcioCliente);
                }
            }
        });
    }


}
