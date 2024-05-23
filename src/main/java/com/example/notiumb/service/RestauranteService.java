package com.example.notiumb.service;

import com.example.notiumb.converter.IRestauranteMapper;
import com.example.notiumb.dto.RestauranteDTO;
import com.example.notiumb.model.Restaurante;
import com.example.notiumb.repository.IComentarioRepository;
import com.example.notiumb.repository.IRestauranteRepository;
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
    private IComentarioRepository iComentarioRepository;


    public List<RestauranteDTO> listarRestaurantes(){
        return restauranteMapper.toDTO(restauranteRepository.findAll());
    }

    public RestauranteDTO getRestauranteByID(Integer id){
        return restauranteMapper.toDTO(restauranteRepository.findById(id).orElse(null));
    }

    /*Crear restaurante*/
   public Restaurante crearRestaurante(RestauranteDTO restauranteDTO){

        Restaurante restaurante = new Restaurante();
        restaurante.setNombre(restauranteDTO.getNombre());
        restaurante.setCif(restaurante.getCif());
        restaurante.setTelefono(restaurante.getTelefono());
        restaurante.setHora_apertura(restaurante.getHora_apertura());
        restaurante.setHora_cierre(restaurante.getHora_cierre());
        restaurante.setDisponible(restaurante.getDisponible());
        restaurante.setImagen_marca(restaurante.getImagen_marca());

       return restauranteRepository.save(restaurante);
   }







}