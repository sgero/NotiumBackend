package com.example.notiumb.service;

import com.example.notiumb.converter.*;
import com.example.notiumb.dto.*;
import com.example.notiumb.model.*;
import com.example.notiumb.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReservaService {

    @Autowired
    private IReservaRepository reservaRepository;

    @Autowired
    private ITurnoRepository turnoRepository;


    @Autowired
    private IRestauranteRepository restauranteRepository;

    @Autowired
    private IRestauranteMapper restauranteMapper;

    @Autowired
    private ITurnoMapper turnoMapper;

    @Autowired
    private IClienteMapper clienteMapper;

    @Autowired
    private IReservaMapper reservaMapper;

    @Autowired
    private IMesaRepository mesaRepository;

    @Autowired
    private IMesaMapper mesaMapper;

    @Autowired
    private IClienteRepository clienteRepository;

    public List<ReservaDTO> getAll() {
        return reservaMapper.toDTO(reservaRepository.findAllByActivoIsTrue());
    }

    public Reserva getReservaById(@Param("id") Integer id) {
        return reservaRepository.findByIdAndActivoIsTrue(id);
    }

    public List<ReservaDTO> getReservaPorRestaurante(Integer id){
        List<Reserva> listadoReservas = reservaRepository.findAllByRestauranteIdAndActivoIsTrue(id);
        return reservaMapper.toDTO(listadoReservas);
    }


    public List<MesaDTO> comprobarTurno(ReservaDTO reservaDTO) {
        LocalDate fecha = reservaDTO.getFecha();
        int numPersonas = reservaDTO.getNumPersonas();

        Restaurante restaurante = restauranteRepository.findTopById(reservaDTO.getRestauranteDTO().getId());
        Turno turno = turnoRepository.findTopById(reservaDTO.getTurnoDTO().getId());

        // Obtener reservas existentes para el mismo restaurante, fecha y turno
        List<Reserva> reservas = reservaRepository.findByFechaEqualsAndRestauranteEqualsAndTurnoEquals(fecha, restaurante, turno);

        // Obtener todas las mesas del restaurante
        List<Mesa> mesas = mesaRepository.findByRestauranteEquals(restaurante);

        // Filtrar mesas ocupadas
        List<Mesa> mesasCogidas = reservas.stream().map(Reserva::getMesa).collect(Collectors.toList());
        mesas.removeAll(mesasCogidas);

        // Ordenar mesas por número de plazas de forma ascendente (de menor a mayor)
        mesas.sort(Comparator.comparingInt(Mesa::getNumPlazas));

        List<MesaDTO> mesasParaReserva = new ArrayList<>();
        int plazasDisponibles = 0;

        // Buscar mesas que puedan acomodar el número de personas requerido
        for (Mesa mesa : mesas) {
            if (plazasDisponibles >= numPersonas) {
                break;
            }
            if (mesa.getNumPlazas() >= numPersonas) {
                // Priorizar la mesa que tenga un número de plazas igual o mayor al número de personas requerido
                mesasParaReserva.add(mesaMapper.toDTO(mesa));
                plazasDisponibles += mesa.getNumPlazas();
            }
        }

        // Verificar si hay suficientes mesas para acomodar a las personas
        if (plazasDisponibles >= numPersonas) {
            return mesasParaReserva;
        } else {
            // Si no hay suficientes mesas, devolver una lista vacía
            return new ArrayList<>();
        }
    }



    public class CodigoReservaGenerator {
        private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        private static final int LENGTH = 8;
        private static final Random RANDOM = new SecureRandom();

        public static String generate() {
            StringBuilder sb = new StringBuilder(LENGTH);
            for (int i = 0; i < LENGTH; i++) {
                sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
            }
            return sb.toString();
        }}




    public ReservaDTO crearReserva(DatosReservaDTO reservaDTO) {
        // Comprueba la disponibilidad de mesas
        Cliente clienteact = clienteRepository.findByIdUser(reservaDTO.getUsuarioDTO().getId());

        ReservaDTO reservaDTO1 = new ReservaDTO();
        reservaDTO1.setFecha(reservaDTO.getFecha().plusDays(1));
        reservaDTO1.setNumPersonas(reservaDTO.getNumPersonas());
        reservaDTO1.setRestauranteDTO(reservaDTO.getRestauranteDTO());
        reservaDTO1.setTurnoDTO(reservaDTO.getTurnoDTO());
        reservaDTO1.setClienteDTO(clienteMapper.toDTO(clienteact));

        List<MesaDTO> mesasDisponibles = comprobarTurno(reservaDTO1);

        if (mesasDisponibles.isEmpty()) {
            throw new RuntimeException("No hay suficientes mesas disponibles para acomodar a " + reservaDTO.getNumPersonas() + " personas.");
        }

        // Generar un código de reserva único
        String codigoReserva;
        do {
            codigoReserva = CodigoReservaGenerator.generate();
        } while (reservaRepository.existsByCodigoReserva(codigoReserva));

        // Crear y guardar reservas para cada mesa disponible
        for (MesaDTO mesaDTO : mesasDisponibles) {
            Reserva nuevaReserva = new Reserva();
            nuevaReserva.setCodigoReserva(codigoReserva);
            nuevaReserva.setFecha(reservaDTO.getFecha().plusDays(1));
            nuevaReserva.setCliente(clienteact);
            nuevaReserva.setRestaurante(restauranteMapper.toEntity(reservaDTO.getRestauranteDTO()));
            nuevaReserva.setMesa(mesaMapper.toEntity(mesaDTO));
            nuevaReserva.setTurno(turnoMapper.toEntity(reservaDTO.getTurnoDTO()));
            nuevaReserva.setActivo(true); // Inicializar activo

            reservaRepository.save(nuevaReserva);

            // Actualizar el estado de la mesa a reservada
            Mesa mesa = mesaRepository.findById(mesaDTO.getId()).orElseThrow(() -> new RuntimeException("Mesa no encontrada"));
            mesa.setReservada(true);
            mesaRepository.save(mesa);
        }

        // Añadir el código de reserva al DTO
        reservaDTO1.setCodigoReserva(codigoReserva);

        return reservaDTO1;
    }





    public ReservaDTO modificarReserva(Integer id, ReservaDTO reservaDTO) {
        // Obtener la reserva existente de la base de datos por su ID
        Reserva reservaExistente = reservaRepository.findByIdAndActivoIsTrue(id);


        // Comprobar disponibilidad de mesas para la nueva configuración
        List<MesaDTO> mesasDisponibles = comprobarTurno(reservaDTO);

        if (mesasDisponibles.isEmpty()) {
            throw new RuntimeException("No hay suficientes mesas disponibles para acomodar a " + reservaDTO.getNumPersonas() + " personas.");
        }

        // Obtener el turno asociado a la reserva por su ID
        Turno turno = turnoRepository.findById(reservaDTO.getTurnoDTO().getId())
                .orElseThrow(() -> new RuntimeException("Turno no encontrado con ID: " + reservaDTO.getTurnoDTO().getId()));

        // Actualizar la reserva existente con los datos del DTO recibido
        reservaExistente.setCodigoReserva(reservaDTO.getCodigoReserva());
        reservaExistente.setActivo(reservaDTO.getActivo() != null ? reservaDTO.getActivo() : true); // Inicializar activo si es nulo
        reservaExistente.setFecha(reservaDTO.getFecha());
        reservaExistente.setCliente(clienteMapper.toEntity(reservaDTO.getClienteDTO()));
        reservaExistente.setTurno(turno); // Asignar el nuevo turno
        reservaExistente.setRestaurante(restauranteMapper.toEntity(reservaDTO.getRestauranteDTO()));
        reservaExistente.setMesa(mesaMapper.toEntity(mesasDisponibles.get(0))); // Asignar una mesa disponible

        // Guardar la reserva actualizada en la base de datos
        reservaRepository.save(reservaExistente);

        // Convertir la entidad Reserva de vuelta a ReservaDTO y retornarla
        return reservaMapper.toDTO(reservaExistente);
    }




    public void eliminarReserva(Integer id) {
        // Obtener la reserva por su ID
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada con ID: " + id));

        // Actualizar el campo activo a false en lugar de eliminar físicamente
        reserva.setActivo(false);

        // Guardar la reserva actualizada en la base de datos
        reservaRepository.save(reserva);
    }


    public List<TurnoDTO> comprobarTurnosDisponibles(int numPersonas, LocalDate fecha, Integer restauranteId) {
        // Obtener el restaurante
        Restaurante restaurante = restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new RuntimeException("Restaurante no encontrado"));

        // Obtener todas las reservas para el restaurante y la fecha especificada
        List<Reserva> reservas = reservaRepository.findByFechaAndRestaurante(fecha, restaurante);

        // Obtener todas las mesas del restaurante
        List<Mesa> mesas = mesaRepository.findByRestaurante(restaurante);

        // Agrupar las reservas por turno
        Map<Turno, List<Reserva>> reservasPorTurno = reservas.stream()
                .collect(Collectors.groupingBy(Reserva::getTurno));

        List<TurnoDTO> turnosDisponibles = new ArrayList<>();

        for (Turno turno : turnoRepository.findByRestauranteAndActivo(restaurante, true)) {
            // Obtener las reservas para el turno actual
            List<Reserva> reservasTurno = reservasPorTurno.getOrDefault(turno, new ArrayList<>());

            // Filtrar mesas ocupadas
            List<Mesa> mesasCogidas = reservasTurno.stream().map(Reserva::getMesa).collect(Collectors.toList());
            List<Mesa> mesasDisponibles = new ArrayList<>(mesas);
            mesasDisponibles.removeAll(mesasCogidas);

            // Ordenar mesas por número de plazas de forma ascendente (de menor a mayor)
            mesasDisponibles.sort(Comparator.comparingInt(Mesa::getNumPlazas));

            int plazasDisponibles = 0;

            // Buscar mesas que puedan acomodar el número de personas requerido
            for (Mesa mesa : mesasDisponibles) {
                if (plazasDisponibles >= numPersonas) {
                    break;
                }
                plazasDisponibles += mesa.getNumPlazas();
            }

            // Verificar si hay suficientes plazas disponibles
            if (plazasDisponibles >= numPersonas) {
                turnosDisponibles.add(turnoMapper.toDTO(turno));
            }
        }
        return turnosDisponibles;
    }

    public List<ReservaDTO> reservasPorUsuario(ReservaTiempoDTO infoReserva){
        List<Reserva> reservasPasadas = new ArrayList<>();
        List<Reserva> reservasFuturas = new ArrayList<>();
        List<ReservaDTO> reservasAEnviar = new ArrayList<>();
        List<Reserva> todasLasRervas = reservaRepository.reservasPorUsuario(infoReserva.getId_usuario());

        for (Reserva r : todasLasRervas) {
            if (r.getFecha().isAfter(LocalDate.now())) {
                reservasFuturas.add(r);
            }else if (r.getFecha().isBefore(LocalDate.now())) {
                reservasPasadas.add(r);
            }
        }

        if(infoReserva.getTipoReserva().equals("futuras")){
            reservasAEnviar = reservaMapper.toDTO(reservasFuturas);
        } else if (infoReserva.getTipoReserva().equals("pasadas")) {
            reservasAEnviar = reservaMapper.toDTO(reservasPasadas);
        }

        return reservasAEnviar;

    }

    public List<ReservaDTO> reservasFechaTurno(ReservaMesasDTO info){

        List<Reserva> reservaList = reservaRepository.reservaFechaTurno(info.getRestauranteDTO().getId(), info.getTurnoDTO().getId(), info.getFecha());

        return reservaMapper.toDTO(reservaList);
    }


}




