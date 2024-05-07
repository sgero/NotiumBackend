package com.example.notiumb.service;

import com.example.notiumb.converter.IComentarioMapper;
import com.example.notiumb.dto.ComentarioDTO;
import com.example.notiumb.dto.ComprobarCodigoDTO;
import com.example.notiumb.model.Comentario;
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

    public Boolean comprobarValoracion(ComprobarCodigoDTO info) {

        List<String> codigosReserva = iReservaRepository.listadoCodigosReserva(info.getId_restaurante());
        List<Date> listadoFechas = iReservaRepository.listadoReservaFecha(info.getId_restaurante());

        //Transformar fechas a LocalDate
        List<LocalDate> fechasTransformadas1 = new ArrayList<>();
        for (Date f : listadoFechas) {
            fechasTransformadas1.add(f.toLocalDate());
        }

        for (String cr : codigosReserva) {
            for (LocalDate fecha : fechasTransformadas1) {
                if (cr.equals(info.getCodigoReserva()) && fecha.isBefore(LocalDate.now())) {
                    return true;
                }
                listadoFechas.remove(0);
                break;
            }
        }

        return false;
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






}







