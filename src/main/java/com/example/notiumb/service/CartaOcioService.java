package com.example.notiumb.service;

import com.example.notiumb.converter.ICartaOcioMapper;
import com.example.notiumb.converter.IOcioNocturnoMapper;
import com.example.notiumb.dto.CartaOcioDTO;
import com.example.notiumb.dto.OcioNocturnoDTO;
import com.example.notiumb.model.CartaOcio;
import com.example.notiumb.model.OcioNocturno;
import com.example.notiumb.repository.ICartaOcioRepository;
import com.example.notiumb.repository.IOcioNocturnoRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public CartaOcioDTO getById(@Param("id") Integer id) {
        return converter.toDTO(repository.findByIdAndActivoIsTrue(id).orElseThrow(() -> new EntityNotFoundException("CartaOcio not found")));
    }

    public CartaOcioDTO save(CartaOcioDTO cartaOcioDTO){
        CartaOcio cartaNueva = converter.toEntity(cartaOcioDTO);
        Integer OcioId = cartaOcioDTO.getOcioNocturnoDTO().getId();
        OcioNocturno ocioNocturno = ocioNocturnoRepository.findByIdAndActivoIsTrue(OcioId).orElseThrow(() -> new EntityNotFoundException("OcioNocturno not found"));
//        cartaNueva = converter.toEntity(cartaOcioDTO);
        cartaNueva.setOcioNocturno(ocioNocturno);
        repository.save(cartaNueva);
        return converter.toDTO(cartaNueva);
    }

    public void delete(Integer id){

        CartaOcio cartaAEliminar = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("CartaOcio not found"));

        if (cartaAEliminar!=null){
            cartaAEliminar.setActivo(false);
            repository.save(cartaAEliminar);
        }
    }


}
