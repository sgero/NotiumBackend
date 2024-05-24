package com.example.notiumb.service;

import com.example.notiumb.converter.IComentarioMapper;
import com.example.notiumb.dto.ComentarioDTO;
import com.example.notiumb.dto.ComprobarCodigoDTO;
import com.example.notiumb.model.Comentario;
import com.example.notiumb.model.Reserva;
import com.example.notiumb.model.Restaurante;
import com.example.notiumb.repository.IComentarioRepository;
import com.example.notiumb.repository.IReservaRepository;
import com.example.notiumb.repository.IRestauranteRepository;
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

   /* public List<ComentarioDTO> listarValoraciones(){

        List<Comentario> listadovaloraciones = iComentarioRepository.findAll();

        return iComentarioMapper.toDTO(listadovaloraciones);
    }*/

    public Integer comprobarValoracion(ComprobarCodigoDTO info) {

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

        // Este código no pertenece al restaurante
        if (infoValoracion.getRestaurante().getId() != info.getId_restaurante()){ return 2; }

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
}







