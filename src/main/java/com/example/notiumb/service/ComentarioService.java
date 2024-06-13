package com.example.notiumb.service;

import com.example.notiumb.converter.IClienteMapper;
import com.example.notiumb.converter.IComentarioMapper;
import com.example.notiumb.converter.IOcioNocturnoMapper;
import com.example.notiumb.converter.IRestauranteMapper;
import com.example.notiumb.dto.*;
import com.example.notiumb.model.Comentario;
import com.example.notiumb.model.Cliente;

import com.example.notiumb.model.OcioNocturno;
import com.example.notiumb.model.Reserva;
import com.example.notiumb.model.Restaurante;
import com.example.notiumb.repository.*;
import com.example.notiumb.utilidades.MapaCodigoRespuestaAPI;
import com.example.notiumb.utilidades.RespuestaDTO;
import com.example.notiumb.utilidades.UtilidadesAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ComentarioService {

    @Autowired
    private IReservaRepository iReservaRepository;
    @Autowired
    private IComentarioRepository icomentarioRepository;
    @Autowired
    private IComentarioMapper iComentarioMapper;
    @Autowired
    private IRestauranteRepository iRestauranteRepository;
    @Autowired
    private IRestauranteMapper restauranteMapper;
    @Autowired
    private IOcioNocturnoMapper ocioNocturnoMapper;
    @Autowired
    private IOcioNocturnoRepository ocioNocturnoRepository;
    @Autowired
    private IEntradaOcioClienteRepository entradaOcioClienteRepository;
    @Autowired
    private IListaOcioClienteRepository listaOcioClienteRepository;
    @Autowired
    private IReservadoOcioClienteRepository reservadoOcioClienteRepository;
    @Autowired
    private IClienteRepository clienteRepository;
    @Autowired
    private IClienteMapper clienteMapper;

   /* public List<ComentarioDTO> listarValoraciones(){

        List<Comentario> listadovaloraciones = iComentarioRepository.findAll();

        return iComentarioMapper.toDTO(listadovaloraciones);
    }*/

    // RESTAURANTE VALORACIONES
    public Integer comprobarValoracionRestaurante(ComprobarCodigoRestauranteDTO info) {

        //Llamadas a repository
        List<String> codigosReserva = iReservaRepository.listadoCodigosReserva(info.getId_restaurante());
        List<Date> listadoFechas = iReservaRepository.listadoReservaFecha(info.getId_restaurante());
        Integer num_valoraciones = icomentarioRepository.conteoCodigoReserva(info.getId_restaurante(), info.getCodigoReserva());
        Reserva infoValoracion = iReservaRepository.buscarValoracion(info.getCodigoReserva());

        //Variables
        Integer i = 0;
        Integer respuesta = 0;
        Integer conteo1 = 1;

        //Este código no existe
        if (infoValoracion == null){ return 1; }

        //Valorar si hay más de una reserva
        for (String cr : codigosReserva) {
            if (cr.equals(info.getCodigoReserva())) {
                if(num_valoraciones >= 1){ return 3; }
                break;
            }
        }

        //Transformar fechas a LocalDate
        List<LocalDate> fechasTransformadas1 = new ArrayList<>();
        for (Date f : listadoFechas) { fechasTransformadas1.add(f.toLocalDate()); }

        for (String cr : codigosReserva) {
            //El codigo y la fecha están bien
            if (cr.equals(info.getCodigoReserva()) && fechasTransformadas1.get(i).isBefore(LocalDate.now())) {return 4;

                // Si la reserva aún no se ha efectuado
            }if(cr.equals(info.getCodigoReserva())){return 5; }
            i++;
        }

        return 6;
    }


    public RespuestaDTO crearValoracion(ComentarioDTO valoracion){
        RespuestaDTO respuestaDTO = new RespuestaDTO();

        try {
                Comentario nuevaValoracion = new Comentario();
                nuevaValoracion.setValoracion(valoracion.getValoracion());
                nuevaValoracion.setTexto(valoracion.getTexto());
                nuevaValoracion.setCodigoReserva(valoracion.getCodigoReserva());
                nuevaValoracion.setFecha_comentario(new Timestamp(System.currentTimeMillis()));
                nuevaValoracion.setActivo(true);
                Cliente clienteValoracion = clienteRepository.findByIdUser(valoracion.getClienteDTO().getId());
                nuevaValoracion.setCliente(clienteValoracion);

                Restaurante restauranteValoracion = iRestauranteRepository.findTopById(valoracion.getRestauranteDTO().getId());
                nuevaValoracion.setRestaurante(restauranteValoracion);

                icomentarioRepository.save(nuevaValoracion);
                UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_200_VALORACION_CREADA);

        } catch (Exception e) {
            e.printStackTrace();
            UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_400_VALORACION_NO_CREADA);}

        return respuestaDTO;
    }

    public Double valoracionMedia(Integer id){

        List<Integer> valoraciones = icomentarioRepository.valorcionesRestaurante(id);

        if(valoraciones.size() != 0) {
            Double sumaValoraciones = 0.0;
            for (Integer valoracion : valoraciones) { sumaValoraciones += valoracion; }
            return sumaValoraciones / valoraciones.size();
        }
        return 10.0;
    }

    public List<Integer> rankingRestaurante(){
        return icomentarioRepository.rankingRestaurantesTop3();
    }

    public List<ComentarioDTO> valoracionPorRestaurante(Integer id){
        return iComentarioMapper.toDTO(icomentarioRepository.findAllByRestauranteIdAndActivoIsTrue(id));
    }

    public List<ClienteDTO> clienteValorciones(Integer id_restaurante){

        List<String> codigosRestaurante = icomentarioRepository.codigoRestaurante(id_restaurante);
        List<Integer> idClientes = new ArrayList<>();
        List<Cliente> listadoClientes = new ArrayList<>();

        for(String codigo : codigosRestaurante){
            Integer IdCliente = icomentarioRepository.IdClienteReserva(codigo);
            idClientes.add(IdCliente);
        }

        for(Integer id : idClientes){
            Cliente cliente =  clienteRepository.findByIdAndActivoIsTrue(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado o no activo"));;
            listadoClientes.add(cliente);
        }

        return clienteMapper.toDTO(listadoClientes);
    }




    //OCIO NOCTURNO

    public Integer comprobarValoracionOcioNocturno(ComprobarCodigoOcioDTO info) {

        //Variables
        Integer i = 0;
        Integer respuesta = 0;
        Integer conteo1 = 1;
        List<String> codigosReserva = new ArrayList<>();
        Timestamp fechaEvento = null;
        Integer idOcioCodigo = 0;

        //Llamadas a repository
        List<Date> listadoFechas = iReservaRepository.listadoReservaFecha(info.getId_ocio());
        Integer num_valoraciones = icomentarioRepository.conteoCodigoReservaOcio(info.getId_ocio(), info.getCodigoReserva());
        Reserva infoValoracion = iReservaRepository.buscarValoracion(info.getCodigoReserva());

        if (info.getCodigoReserva().length() >= 3){
            String primerosDigitos = info.getCodigoReserva().substring(0, 3);
            if (primerosDigitos.equals("EOC")){
                codigosReserva = entradaOcioClienteRepository.listadoCodigosReservaEntrada(info.getId_ocio());
                fechaEvento = entradaOcioClienteRepository.fechaEventoEntrada(info.getId_ocio(), info.getCodigoReserva());
                idOcioCodigo = entradaOcioClienteRepository.idOcioEntrada(info.getCodigoReserva());
            }else if (primerosDigitos.equals("ROC")){
                codigosReserva = reservadoOcioClienteRepository.listadoCodigosReservaReservado(info.getId_ocio());
                fechaEvento = reservadoOcioClienteRepository.fechaEventoReservado(info.getId_ocio(), info.getCodigoReserva());
                idOcioCodigo = reservadoOcioClienteRepository.idOcioReservado(info.getCodigoReserva());
            }else if(primerosDigitos.equals("LOC")){
                codigosReserva = listaOcioClienteRepository.listadoCodigosReservaListado(info.getId_ocio());
                fechaEvento = listaOcioClienteRepository.fechaEventoLista(info.getId_ocio(), info.getCodigoReserva());
                idOcioCodigo = listaOcioClienteRepository.idOcioLista(info.getCodigoReserva());

                //Este código no existe
            } else { return 1; }

            //Este código no existe
        }else{ return 1; }

        // Este código no pertenece al ocio nocturno
        if (idOcioCodigo != info.getId_ocio()){ return 2; }

        //Valorar si hay más de una reserva
        if(num_valoraciones >= 1){ return 3; }


        for (String cr : codigosReserva) {
            //El codigo y la fecha están bien
            if (cr.equals(info.getCodigoReserva()) && fechaEvento.toLocalDateTime().toLocalDate().isBefore(LocalDate.now())) {return 4;

                // Si la reserva aún no se ha efectuado
            } else if (cr.equals(info.getCodigoReserva())) { return 5;}
        }

        return 6;
    }

    public RespuestaDTO crearValoracionOcioNocturno(ComentarioDTO valoracion){
        RespuestaDTO respuestaDTO = new RespuestaDTO();

        try {
            Comentario nuevaValoracion = new Comentario();
            nuevaValoracion.setValoracion(valoracion.getValoracion());
            nuevaValoracion.setTexto(valoracion.getTexto());
            nuevaValoracion.setCodigoReserva(valoracion.getCodigoReserva());
            nuevaValoracion.setFecha_comentario(new Timestamp(System.currentTimeMillis()));
            nuevaValoracion.setActivo(true);

            Cliente clienteValoracion = clienteRepository.findByIdUser(valoracion.getClienteDTO().getId());
            nuevaValoracion.setCliente(clienteValoracion);

            OcioNocturno ocioNocturno = ocioNocturnoRepository.findTopByIdAndActivoIsTrue(valoracion.getOcioDTO().getId());
            nuevaValoracion.setOcio(ocioNocturno);

            icomentarioRepository.save(nuevaValoracion);
            UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_200_VALORACION_CREADA);

        } catch (Exception e) {
            e.printStackTrace();
            UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_400_VALORACION_NO_CREADA);}

        return respuestaDTO;
    }

    public List<Integer> rankingOcioNocturno(){
        return icomentarioRepository.rankingOcioNocturno();
    }

    public List<ComentarioDTO> valoracionesOcio(Integer id){
        return iComentarioMapper.toDTO(icomentarioRepository.findAllByOcioIdAndActivoIsTrue(id));
    }

}







