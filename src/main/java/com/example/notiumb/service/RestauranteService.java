package com.example.notiumb.service;

import com.example.notiumb.converter.IDireccionMapper;
import com.example.notiumb.converter.IRestauranteMapper;
import com.example.notiumb.dto.RestauranteDTO;
import com.example.notiumb.dto.UserDTO;
import com.example.notiumb.dto.UserRestauranteDTO;
import com.example.notiumb.model.Cliente;
import com.example.notiumb.model.Direccion;
import com.example.notiumb.model.Restaurante;
import com.example.notiumb.model.User;
import com.example.notiumb.repository.IDireccionRepository;
import com.example.notiumb.repository.IRestauranteRepository;
import com.example.notiumb.repository.IUserRepository;
import com.example.notiumb.security.auth.AuthController;
import com.example.notiumb.service.implementation.EmailServiceImpl;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
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
    private IDireccionRepository direccionRepository;

    @Autowired
    private IDireccionMapper direccionMapper;


    public List<RestauranteDTO> listarRestaurantes(){
        return restauranteMapper.toDTO(restauranteRepository.findAll());
    }

    public RestauranteDTO getRestauranteByID(Integer id){
        return restauranteMapper.toDTO(restauranteRepository.findById(id).orElse(null));
    }

    /*Crear restaurante*/
    @Transactional
    public RestauranteDTO crearYModificarRestaurante(UserRestauranteDTO userRestauranteDTO) throws MessagingException {

        if (userRestauranteDTO.getId() == null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(userRestauranteDTO.getUsername());
            userDTO.setEmail(userRestauranteDTO.getEmail());
            userDTO.setPassword(userRestauranteDTO.getPassword());
            userDTO.setRol(userRestauranteDTO.getRol());

            authController.register(userDTO);
            User usuario = userRepository.findTopByUsernameAndActivoTrue(userDTO.getUsername());

            Direccion direccionNueva = new Direccion();
            direccionNueva.setCalle(userRestauranteDTO.getDireccionDTO().getCalle());
            direccionNueva.setNumero(userRestauranteDTO.getDireccionDTO().getNumero());
            direccionNueva.setPuerta(userRestauranteDTO.getDireccionDTO().getPuerta());
            direccionNueva.setCodigoPostal(userRestauranteDTO.getDireccionDTO().getCodigoPostal());
            direccionNueva.setCiudad(userRestauranteDTO.getDireccionDTO().getCiudad());
            direccionNueva.setProvincia(userRestauranteDTO.getDireccionDTO().getProvincia());
            direccionNueva.setPais(userRestauranteDTO.getDireccionDTO().getPais());
            direccionRepository.save(direccionNueva);

            Direccion direccion = direccionRepository.findTopByCalleOrderByIdDesc(userRestauranteDTO.getDireccionDTO().getCalle());

            Restaurante restaurante = new Restaurante();

            restaurante.setNombre(userRestauranteDTO.getNombre());
            restaurante.setCif(userRestauranteDTO.getCif());
            restaurante.setTelefono(userRestauranteDTO.getTelefono());
            restaurante.setHora_apertura(userRestauranteDTO.getHora_apertura());
            restaurante.setHora_cierre(userRestauranteDTO.getHora_cierre());
            restaurante.setDisponible(userRestauranteDTO.getDisponible());
            restaurante.setUser(usuario);
            restaurante.setImagen_marca(userRestauranteDTO.getImagen_marca());
            restaurante.setDireccion(direccion);

            //envio de email de verificacion
            emailService.enviarEmailVerificacionRestaurante(restauranteMapper.toDTO(restaurante));

            return restauranteMapper.toDTO(restauranteRepository.save(restaurante));

        }else {

            Restaurante restauranteModificar = restauranteRepository.findByIdAndActivoIsTrue(userRestauranteDTO.getId());

            if (restauranteModificar == null) {

                return null;

            } else {

                Direccion direccionModificada = restauranteModificar.getDireccion();

                direccionModificada.setCalle(userRestauranteDTO.getDireccionDTO().getCalle());
                direccionModificada.setNumero(userRestauranteDTO.getDireccionDTO().getNumero());
                direccionModificada.setPuerta(userRestauranteDTO.getDireccionDTO().getPuerta());
                direccionModificada.setCodigoPostal(userRestauranteDTO.getDireccionDTO().getCodigoPostal());
                direccionModificada.setCiudad(userRestauranteDTO.getDireccionDTO().getCiudad());
                direccionModificada.setProvincia(userRestauranteDTO.getDireccionDTO().getProvincia());
                direccionModificada.setPais(userRestauranteDTO.getDireccionDTO().getPais());

                direccionRepository.save(direccionModificada);

                restauranteModificar.setNombre(userRestauranteDTO.getNombre());
                restauranteModificar.setCif(userRestauranteDTO.getCif());
                restauranteModificar.setTelefono(userRestauranteDTO.getTelefono());
                restauranteModificar.setHora_apertura(userRestauranteDTO.getHora_apertura());
                restauranteModificar.setHora_cierre(userRestauranteDTO.getHora_cierre());
                restauranteModificar.setDisponible(userRestauranteDTO.getDisponible());
                restauranteModificar.setImagen_marca(userRestauranteDTO.getImagen_marca());
                restauranteModificar.setDireccion(direccionModificada);

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

    public List<RestauranteDTO> listarRestaurantesCategoria(Integer id){

        return restauranteMapper.toDTO(restauranteRepository.findPorClase(id));
    }

    public List<RestauranteDTO> rankingRestaurante(){
        List<Restaurante> listaRestaurantes = restauranteRepository.rankingRestaurantes();
        return restauranteMapper.toDTO(listaRestaurantes);
    }

    public List<RestauranteDTO> restaurantesMasValorados(){
        List<Restaurante> listaRestaurantes = restauranteRepository.restaurantesMasValorados();
        return restauranteMapper.toDTO(listaRestaurantes);
    }
}