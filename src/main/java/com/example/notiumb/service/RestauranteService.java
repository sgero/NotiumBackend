package com.example.notiumb.service;

import com.example.notiumb.converter.IDireccionMapper;
import com.example.notiumb.converter.IRestauranteMapper;
import com.example.notiumb.dto.RestauranteDTO;
import com.example.notiumb.dto.UserDTO;
import com.example.notiumb.dto.UserRestauranteDTO;
import com.example.notiumb.model.Cliente;
import com.example.notiumb.model.Restaurante;
import com.example.notiumb.model.User;
import com.example.notiumb.repository.IRestauranteRepository;
import com.example.notiumb.repository.IUserRepository;
import com.example.notiumb.security.auth.AuthController;
import com.example.notiumb.service.implementation.EmailServiceImpl;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestauranteService {

    @Autowired
    private IRestauranteRepository restauranteRepository;
    @Autowired
    private IRestauranteMapper restauranteMapper;

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private AuthController authController;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IDireccionMapper direccionMapper;


    public List<RestauranteDTO> listarRestaurantes(){
        return restauranteMapper.toDTO(restauranteRepository.findAll());
    }

    public RestauranteDTO getRestauranteByID(Integer id){
        return restauranteMapper.toDTO(restauranteRepository.findById(id).orElse(null));
    }

    /*Crear restaurante*/
    public RestauranteDTO crearYModificarRestaurante(UserRestauranteDTO userRestauranteDTO) throws MessagingException {

        if (userRestauranteDTO.getId() == null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(userRestauranteDTO.getUsername());
            userDTO.setEmail(userRestauranteDTO.getEmail());
            userDTO.setPassword(userRestauranteDTO.getPassword());
            userDTO.setRol(userRestauranteDTO.getRol());

            authController.register(userDTO);
            User usuario = userRepository.findTopByUsernameAndActivoTrue(userDTO.getUsername());

            Restaurante restaurante = new Restaurante();

            restaurante.setNombre(userRestauranteDTO.getNombre());
            restaurante.setCif(userRestauranteDTO.getCif());
            restaurante.setTelefono(userRestauranteDTO.getTelefono());
            restaurante.setHora_apertura(userRestauranteDTO.getHora_apertura());
            restaurante.setHora_cierre(userRestauranteDTO.getHora_cierre());
            restaurante.setDisponible(userRestauranteDTO.getDisponible());
            restaurante.setUser(usuario);
            restaurante.setImagen_marca(userRestauranteDTO.getImagen_marca());
            restaurante.setDireccion(direccionMapper.toEntity(userRestauranteDTO.getDireccionDTO()));

            //envio de email de verificacion
            emailService.enviarEmailVerificacionRestaurante(restauranteMapper.toDTO(restaurante));

            return restauranteMapper.toDTO(restauranteRepository.save(restaurante));

        }else {

            Restaurante restauranteModificar = restauranteRepository.findByIdAndActivoIsTrue(userRestauranteDTO.getId());

            if (restauranteModificar == null) {

                return null;

            } else {

                restauranteModificar.setNombre(userRestauranteDTO.getNombre());
                restauranteModificar.setCif(userRestauranteDTO.getCif());
                restauranteModificar.setTelefono(userRestauranteDTO.getTelefono());
                restauranteModificar.setHora_apertura(userRestauranteDTO.getHora_apertura());
                restauranteModificar.setHora_cierre(userRestauranteDTO.getHora_cierre());
                restauranteModificar.setDisponible(userRestauranteDTO.getDisponible());
                restauranteModificar.setImagen_marca(userRestauranteDTO.getImagen_marca());
                restauranteModificar.setDireccion(direccionMapper.toEntity(userRestauranteDTO.getDireccionDTO()));

                restauranteRepository.save(restauranteModificar);
                return restauranteMapper.toDTO(restauranteModificar);

            }

        }

    }

    public Restaurante getRestauranteByCif(String cif){
        return restauranteRepository.findByCif(cif);
    }



    public void actualizarRestaurante(Restaurante restaurante) {
        restauranteRepository.save(restaurante);
    }
}