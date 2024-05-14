package com.example.notiumb.service;

import com.example.notiumb.converter.IEntradaOcioClienteMapper;
import com.example.notiumb.converter.IListaOcioClienteMapper;
import com.example.notiumb.dto.EntradaOcioClienteDTO;
import com.example.notiumb.model.*;
import com.example.notiumb.repository.*;
import com.example.notiumb.utilidades.MapaCodigoRespuestaAPI;
import com.example.notiumb.utilidades.RespuestaDTO;
import com.example.notiumb.utilidades.UtilidadesAPI;
import jdk.jfr.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private IEntradaOcioClienteMapper entradaOcioClienteMapper;
    @Autowired
    private IClienteRepository clienteRepository;
    @Autowired
    private IListaOcioClienteMapper listaOcioClienteMapper;

    public boolean aforoNoCompleto(Evento evento, List<EntradaOcioCliente> entradasOcioCliente, ReservadoOcioCliente reservadoOcioCliente, List<ListaOcioCliente> listaOcioCliente){
        Integer aforoEvento = evento.getAforo();

        EntradaOcio entradaOcio = entradaOcioRepository.findEntradaOcioByEventoIdAndActivoIsTrue(evento.getId());
        ReservadoOcio reservadoOcio = reservadoOcioRepository.findReservadoOcioByEventoIdAndActivoIsTrue(evento.getId());
        List<ListaOcio> listaOcio = listaOcioRepository.findAllByEventoId(evento.getId());

        Integer entradasVendidas = entradaOcioClienteRepository.cantidadEntradasVendidas(entradaOcio.getId());
        Integer reservadosVendidos = reservadoOcioClienteRepository.cantidadReservadosVendidos(reservadoOcio.getId());
        Integer personasTotalesReservadosVendidos = reservadoOcioClienteRepository.cantidadPersonas(reservadoOcio.getId());
        Double invitacionesTotalesLista = listaOcio.stream()
                .mapToDouble(ListaOcio::getTotal_invitaciones)
                .sum();
        Integer clientesApuntadosALista = listaOcioClienteRepository.cantidadClientesTotales(evento.getId());

        if (entradasOcioCliente != null){
            return entradasVendidas < entradaOcio.getTotalEntradas()
                    && (entradasOcioCliente.size() + entradasVendidas) < entradaOcio.getTotalEntradas()
                    && (entradasOcioCliente.size() + entradasVendidas) < aforoEvento;
        }else if (reservadoOcioCliente != null){
            return reservadoOcioCliente.getCantidad_personas() < reservadoOcio.getPersonasMaximasPorReservado()
                    && reservadosVendidos < reservadoOcio.getReservadosDisponibles()
                    && (reservadoOcioCliente.getCantidad_personas() + personasTotalesReservadosVendidos) < aforoEvento;
        }else if (listaOcioCliente != null){
            return clientesApuntadosALista < invitacionesTotalesLista
                    && (listaOcioCliente.size() + clientesApuntadosALista) < invitacionesTotalesLista
                    && (listaOcioCliente.size() + clientesApuntadosALista) < aforoEvento;
        }
        return false;
    }

    public RespuestaDTO comprarEntradaNormal(Integer idEvento, Integer idCliente, Integer entradaOcioALaVenta, List<EntradaOcioClienteDTO> entradasOcioClienteLista) {
        Cliente cliente = clienteRepository.findById(idCliente).orElse(null);
        Evento evento = eventoRepository.findById(idEvento).orElse(null);
        EntradaOcio entradaOcio = entradaOcioRepository.findById(entradaOcioALaVenta).orElse(null);
        RespuestaDTO respuestaDTO = new RespuestaDTO();
        if (evento != null && cliente != null && entradaOcio != null && entradasOcioClienteLista != null){
            List<EntradaOcioCliente> listaEntradasCompradas = listaOcioClienteMapper.toEntity(entradasOcioClienteLista);
            if (aforoNoCompleto(evento, listaEntradasCompradas, null, null)){
                entradasOcioClienteLista.forEach(e -> EntradaOcioCliente.builder()
                        .fechaCompra(Timestamp.from(Instant.from(LocalDate.now())))
                        .codigo("EOC" + "1")
                        .cliente(cliente)
                        .entradaOcio(entradaOcio)
                        .build()
                );

                entradaOcioClienteRepository.saveAll(listaEntradasCompradas);
                UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_200_ENTRADAS_COMPRADAS, entradasOcioClienteLista);
            }
        }else {
            UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_ERROR_EVENTO);
        }
        return respuestaDTO;
    }
}
