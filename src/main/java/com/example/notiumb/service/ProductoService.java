package com.example.notiumb.service;


import com.example.notiumb.converter.IProductoMapper;
import com.example.notiumb.dto.ProductoDTO;
import com.example.notiumb.dto.TokenDTO;
import com.example.notiumb.model.CartaRestaurante;
import com.example.notiumb.model.Restaurante;
import com.example.notiumb.model.User;
import com.example.notiumb.repository.ICartaRestauranteRespository;
import com.example.notiumb.repository.IProductoRepository;

import com.example.notiumb.repository.IRestauranteRepository;
import com.example.notiumb.repository.IUserRepository;
import com.example.notiumb.security.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductoService {

    @Autowired
    private IProductoRepository productoRepository;

    @Autowired
    private IProductoMapper productoMapper;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRestauranteRepository restauranteRepository;

    @Autowired
    private ICartaRestauranteRespository cartaRestauranteRespository;

    public ProductoDTO crearProducto(ProductoDTO productoDTO) {

        return productoMapper.toDTO(productoRepository.save(productoMapper.toEntity(productoDTO)));

    }

    public List<ProductoDTO> listarProducto(TokenDTO tokenDTO) {
        String tokensio = tokenDTO.getToken();
        //String username = jwtService.extractUsername(tokenDTO.getToken());
        User user = userRepository.findTopByUsernameAndActivoTrue("Pruebas");
        Restaurante restaurante = restauranteRepository.findTopByUserEquals(user);
        CartaRestaurante carta = cartaRestauranteRespository.findTopByRestauranteEquals(restaurante);

        return productoMapper.toDTO(productoRepository.findByCartaResEqualsAndActivoTrue(carta));

    }

}
