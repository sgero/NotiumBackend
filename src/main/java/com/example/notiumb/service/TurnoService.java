package com.example.notiumb.service;

import com.example.notiumb.converter.ITurnoMapper;
import com.example.notiumb.dto.TurnoDTO;
import com.example.notiumb.model.Restaurante;
import com.example.notiumb.model.Turno;
import com.example.notiumb.repository.IRestauranteRepository;
import com.example.notiumb.repository.ITurnoRepository;
import com.example.notiumb.utilidades.MapaCodigoRespuestaAPI;
import com.example.notiumb.utilidades.RespuestaDTO;
import com.example.notiumb.utilidades.UtilidadesAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService {

    @Autowired
    private ITurnoRepository iTurnoRepository;
    @Autowired
    private ITurnoMapper turnoMapper;

    @Autowired
    private IRestauranteRepository iRestauranteRepository;


    public RespuestaDTO crearTurno(TurnoDTO turnoDTO) {
        RespuestaDTO respuestaDTO = new RespuestaDTO();

        try {

        /*    List<String> horariosAperturas = iTurnoRepository.horaInicioTurnosPorRestaurante(turnoDTO.getRestauranteDTO().getId());
            List<String> horariosCierre = iTurnoRepository.horaFinTurnosPorRestaurante(turnoDTO.getRestauranteDTO().getId());

            String mismos_horarios = "no";

            for (String hora_apertura : horariosAperturas ){
                for (String hora_cierre : horariosCierre){
                    if (hora_apertura.equals(turnoDTO.getHora_inicio()) && hora_cierre.equals(turnoDTO.getHora_fin())){
                        mismos_horarios = "si";
                    }
                    horariosCierre.remove(0);
                    break;
                }
            }
            if(mismos_horarios != "si") {*/
                Turno nuevoTurno = new Turno();
                nuevoTurno.setHora_inicio(turnoDTO.getHora_inicio());
                nuevoTurno.setHora_fin(turnoDTO.getHora_fin());
                nuevoTurno.setActivo(true);

                Restaurante restauranteTurno = iRestauranteRepository.findTopById(turnoDTO.getRestauranteDTO().getId());
                nuevoTurno.setRestaurante(restauranteTurno);

                iTurnoRepository.save(nuevoTurno);
                UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_200_TURNO_CREADO);
            //}

        } catch (Exception e) {UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_400_TURNO_NO_CREADO);}

        return respuestaDTO;
    }

    public List<TurnoDTO> listarTurnos(){

        List<Turno> turnos = iTurnoRepository.findAll();

        return turnoMapper.toDTO(turnos);
    }



    public RespuestaDTO eliminarTurno(Integer id) {
        RespuestaDTO respuestaDTO = new RespuestaDTO();
        Turno turnoAEliminar = iTurnoRepository.findById(id).orElse(null);
        try{
            if (turnoAEliminar != null && turnoAEliminar.getActivo() != false){
                turnoAEliminar.setActivo(false);
                iTurnoRepository.save(turnoAEliminar);
                UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_200_TURNO_DESACTIVADO);
            }
        }catch (Exception e){
            UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_400_TURNO_NO_DESACTIVADO);
        }
        return respuestaDTO;
    }

    public List<TurnoDTO> turnosRestaurante(Integer id){
        return turnoMapper.toDTO(iTurnoRepository.findAllByRestauranteIdAndActivoIsTrue(id));
    }
}
