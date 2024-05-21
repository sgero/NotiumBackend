package com.example.notiumb.service;

import com.example.notiumb.converter.ICartaOcioMapper;
import com.example.notiumb.converter.IOcioNocturnoMapper;
import com.example.notiumb.dto.CartaOcioDTO;
import com.example.notiumb.dto.OcioNocturnoDTO;
import com.example.notiumb.model.CartaOcio;
import com.example.notiumb.model.OcioNocturno;
import com.example.notiumb.repository.ICartaOcioRepository;
import com.example.notiumb.repository.IOcioNocturnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartaOcioService {

    @Autowired
    private ICartaOcioRepository repository;
    @Autowired
    private IOcioNocturnoRepository ocioNocturnoRepository;
    @Autowired
    private ICartaOcioMapper converter;
    @Autowired
    private IOcioNocturnoMapper ocioNocturnoMapper;

    public List<CartaOcioDTO> getAll() {
        return converter.toDTO(repository.findAllByActivoIsTrue());
    }

    public CartaOcio getById(@Param("id") Integer id) {
        return repository.findByIdAndActivoIsTrue(id).orElse(null);
    }
    public CartaOcioDTO save(CartaOcioDTO cartaOcioDTO){
        CartaOcioDTO cartaNuevaDTO = new CartaOcioDTO();
        cartaNuevaDTO.setOcioNocturnoDTO(cartaOcioDTO.getOcioNocturnoDTO());
        repository.save(converter.toEntity(cartaNuevaDTO));
        return cartaNuevaDTO;
    }

    public void delete(Integer id){

        CartaOcio cartaAEliminar = repository.findById(id).orElse(null);

        if (cartaAEliminar!=null){
            cartaAEliminar.setActivo(false);
            repository.save(cartaAEliminar);
        }
    }


}
