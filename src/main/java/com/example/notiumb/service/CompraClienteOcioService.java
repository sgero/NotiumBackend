package com.example.notiumb.service;

import com.example.notiumb.model.*;
import com.example.notiumb.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public boolean aforoNoCompleto(Evento evento, List<EntradaOcioCliente> entradasOcioCliente, ReservadoOcioCliente reservadoOcioCliente, List<ListaOcioCliente> listaOcioCliente){
        Integer aforoEvento = evento.getAforo();

        EntradaOcio entradaOcio = entradaOcioRepository.findEntradaOcioByEventoIdAndActivoIsTrue(evento.getId());
        ReservadoOcio reservadoOcio = reservadoOcioRepository.findReservadoOcioByEventoIdAndActivoIsTrue(evento.getId());
        List<ListaOcio> listaOcio = listaOcioRepository.findListaOcioByEventoIdAndActivoIsTrue(evento.getId());

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
}
