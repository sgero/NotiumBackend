package com.example.notiumb.service;

import com.example.notiumb.converter.ICartaOcioMapper;
import com.example.notiumb.converter.IOcioNocturnoMapper;
import com.example.notiumb.dto.CartaOcioDTO;
import com.example.notiumb.dto.OcioNocturnoDTO;
import com.example.notiumb.model.CartaOcio;
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
    private ICartaOcioMapper converter;
    @Autowired
    private IOcioNocturnoMapper ocioNocturnoMapper;

    public List<CartaOcioDTO> getAll() {
        return converter.toDTO(repository.findAll());
    }

    public CartaOcio getById(@Param("id") Integer id) {
        return repository.findById(id).orElse(null);
    }
    public CartaOcioDTO save(CartaOcioDTO cartaOcioDTO ,OcioNocturnoDTO ocioNocturnoDTO){
        if (cartaOcioDTO.getId()==null){
            CartaOcioDTO CartaNueva = CartaOcioDTO.builder()
                    .ocioNocturnoDTO(ocioNocturnoDTO)
                    .build();
            repository.save(converter.toEntity(CartaNueva));
            return CartaNueva;
        }
        CartaOcio cartaModificar = repository.findById(cartaOcioDTO.getId()).orElse(null);
        if (cartaModificar==null){
            return null;
        }else{
            cartaModificar.setOcioNocturno(ocioNocturnoMapper.toEntity(ocioNocturnoDTO));
            repository.save(cartaModificar);
        }

        return converter.toDTO(cartaModificar);
    }

    public String delete(CartaOcioDTO cartaOcioDTO){
        CartaOcio cataAEliminar = repository.findById(cartaOcioDTO.getId()).orElse(null);

        if (cataAEliminar!=null){
            cataAEliminar.setActivo(false);
            repository.save(cataAEliminar);
            return "Datos eliminados correctamente";
        }
        return "No se ha podido eliminar la carta";
    }


}
