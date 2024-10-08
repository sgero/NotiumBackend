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

    public CartaOcioDTO getById(@Param("id") Integer id) {
        return converter.toDTO(repository.findByIdAndActivoIsTrue(id).orElse(null));
    }
    public CartaOcioDTO getByOcioId(@Param("id") Integer idOcio) {
        return converter.toDTO(repository.findByOcioNocturnoId(idOcio));
    }

    public CartaOcioDTO save(OcioNocturnoDTO cartaOcioDTO){
        
        OcioNocturno ocioNocturno = ocioNocturnoRepository.findByIdAndActivoIsTrue(cartaOcioDTO.getId()).orElse(null);
        CartaOcio cartaExistente = repository.findByOcioNocturnoId(cartaOcioDTO.getId());
        if (cartaExistente ==null) {
            CartaOcio cartaNueva = new CartaOcio();
            cartaNueva.setOcioNocturno(ocioNocturno);
            repository.save(cartaNueva);
            return converter.toDTO(cartaNueva);
        }
        else {
            cartaExistente.setActivo(true);
            repository.save(cartaExistente);
            return converter.toDTO(cartaExistente);
        }
    }

    public void delete(@Param("id") Integer idOcio){

        CartaOcio cartaAEliminar = repository.findByOcioNocturnoId(idOcio);

        if (cartaAEliminar!=null){
            cartaAEliminar.setActivo(false);
            repository.save(cartaAEliminar);
        }
    }


}
