package com.example.notiumb.service;

import com.example.notiumb.converter.IRestauranteMapper;
import com.example.notiumb.dto.RestauranteDTO;
import com.example.notiumb.model.Restaurante;
import com.example.notiumb.model.User;
import com.example.notiumb.repository.IRestauranteRepository;
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


    public List<RestauranteDTO> listarRestaurantes(){
        return restauranteMapper.toDTO(restauranteRepository.findAll());
    }

    public RestauranteDTO getRestauranteByID(Integer id){
        return restauranteMapper.toDTO(restauranteRepository.findById(id).orElse(null));
    }

    /*Crear restaurante*/
   public Restaurante crearRestaurante(RestauranteDTO restauranteDTO) throws MessagingException {

        Restaurante restaurante = new Restaurante();
        restaurante.setNombre(restauranteDTO.getNombre());
        restaurante.setCif(restaurante.getCif());
        restaurante.setTelefono(restaurante.getTelefono());
        restaurante.setHora_apertura(restaurante.getHora_apertura());
        restaurante.setHora_cierre(restaurante.getHora_cierre());
        restaurante.setDisponible(restaurante.getDisponible());
        restaurante.setImagen_marca(restaurante.getImagen_marca());

        emailService.enviarEmailVerificacionRestaurante(restauranteMapper.toDTO(restaurante));

       return restauranteRepository.save(restaurante);



   }

    public Restaurante getRestauranteByCif(String cif){
        return restauranteRepository.findByCif(cif);
    }



    public void actualizarRestaurante(Restaurante restaurante) {
        restauranteRepository.save(restaurante);
    }
}