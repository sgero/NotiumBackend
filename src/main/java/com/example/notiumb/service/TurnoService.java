package com.example.notiumb.service;

import com.example.notiumb.converter.ITurnoMapper;
import com.example.notiumb.dto.ReservaTurnosDTO;
import com.example.notiumb.dto.TurnoDTO;
import com.example.notiumb.dto.TurnoSemanaDTO;
import com.example.notiumb.model.Restaurante;
import com.example.notiumb.model.Turno;
import com.example.notiumb.model.TurnosDiasSemana;
import com.example.notiumb.model.enums.DiasARepetirCicloEventoOcio;
import com.example.notiumb.repository.IRestauranteRepository;
import com.example.notiumb.repository.ITurnoRepository;
import com.example.notiumb.repository.ITurnoSemanaRepository;
import com.example.notiumb.utilidades.MapaCodigoRespuestaAPI;
import com.example.notiumb.utilidades.RespuestaDTO;
import com.example.notiumb.utilidades.UtilidadesAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class TurnoService {

    @Autowired
    private ITurnoRepository iTurnoRepository;
    @Autowired
    private ITurnoMapper turnoMapper;

    @Autowired
    private IRestauranteRepository iRestauranteRepository;

    @Autowired
    private ITurnoSemanaRepository turnoSemanaRepository;


    public RespuestaDTO crearTurnoCompleto(TurnoDTO turnoDTO, List<DiasARepetirCicloEventoOcio> diasTurno){
        RespuestaDTO respuestaDTO = new RespuestaDTO();

        try{

            Turno nuevoTurno = new Turno();
            nuevoTurno.setHora_inicio(turnoDTO.getHora_inicio());
            nuevoTurno.setHora_fin(turnoDTO.getHora_fin());
            nuevoTurno.setActivo(true);

            Restaurante restauranteTurno = iRestauranteRepository.findTopById(turnoDTO.getRestauranteDTO().getId());
            nuevoTurno.setRestaurante(restauranteTurno);

            diasTurno.forEach(d->{
                TurnosDiasSemana turnoSemana = new TurnosDiasSemana();
                turnoSemana.setDias(d);
                turnoSemana.setTurno(nuevoTurno);
                turnoSemanaRepository.save(turnoSemana);
            });

        }catch (Exception e) {UtilidadesAPI.setearMensaje(respuestaDTO, MapaCodigoRespuestaAPI.CODIGO_400_TURNO_NO_CREADO);}

        return respuestaDTO;
    }


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

    public List<TurnoDTO> turnosPorReservaFecha(ReservaTurnosDTO info){
        List<Turno> listadoTurnosReserva = iTurnoRepository.turnosReservasFecha(info.getId_restaurante(),info.getFecha());
        return turnoMapper.toDTO(listadoTurnosReserva);
    }


    public List<TurnoDTO> turnosPorFecha(ReservaTurnosDTO info){

        DayOfWeek diaDeLaSemana = info.getFecha().getDayOfWeek();
        Integer numDia = 0;

        switch (diaDeLaSemana) {
            case MONDAY:
                numDia = 1;
            case TUESDAY:
                numDia = 2;
            case WEDNESDAY:
                numDia = 3;
            case THURSDAY:
                numDia = 4;
            case FRIDAY:
                numDia = 5;
            case SATURDAY:
                numDia = 6;
            case SUNDAY:
                numDia = 7;
        }
        List<Turno> turnoRestaurante = iTurnoRepository.turnosFecha(info.getId_restaurante(), numDia);

        return turnoMapper.toDTO(turnoRestaurante);
    }

    public static Integer obtenerDiaDeLaSemana(LocalDate fecha) {

        DayOfWeek diaDeLaSemana = fecha.getDayOfWeek();

        switch (diaDeLaSemana) {
            case MONDAY:
                return 1;
            case TUESDAY:
                return 2;
            case WEDNESDAY:
                return 3;
            case THURSDAY:
                return 4;
            case FRIDAY:
                return 5;
            case SATURDAY:
                return 6;
            case SUNDAY:
                return 7;
        }
        return 0;

    }







}
