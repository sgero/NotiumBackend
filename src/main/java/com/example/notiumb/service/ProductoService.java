package com.example.notiumb.service;


import com.example.notiumb.converter.IProductoFormatoMapper;
import com.example.notiumb.converter.IProductoMapper;
import com.example.notiumb.dto.*;
import com.example.notiumb.model.CartaRestaurante;
import com.example.notiumb.model.Producto;
import com.example.notiumb.model.Restaurante;
import com.example.notiumb.model.User;
import com.example.notiumb.model.enums.TipoCategoria;
import com.example.notiumb.repository.*;

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

    @Autowired
    private IProductoFormatoRepository productoFormatoRepository;

    @Autowired
    private IProductoFormatoMapper productoFormatoMapper;

    public ProductoDTO crearProducto(ProductoAuxDTO productoAuxDTO) {

        String tokensio = productoAuxDTO.getUsername();
        //String username = jwtService.extractUsername(productoDTO.getUsername());
        User user = userRepository.findTopByUsernameAndActivoTrue("Pruebas");
        Restaurante restaurante = restauranteRepository.findTopByUserEquals(user);
        CartaRestaurante carta = cartaRestauranteRespository.findTopByRestauranteEquals(restaurante);
        CartaRestauranteDTO cartaToSet = new CartaRestauranteDTO();
        cartaToSet.setId(carta.getId());
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setNombre(productoAuxDTO.getNombre());
        productoDTO.setTipoCategoria(TipoCategoria.valueOf(productoAuxDTO.getTipoCategoria()));
        productoDTO.setCartaRes(cartaToSet);
        productoDTO.setActivo(true);


        return productoMapper.toDTO(productoRepository.save(productoMapper.toEntity(productoDTO)));

    }

    public List<ListadoProductosDTO> listarProducto(TokenDTO tokenDTO) {
        String tokensio = tokenDTO.getToken();
        //String username = jwtService.extractUsername(tokenDTO.getToken());
        User user = userRepository.findTopByUsernameAndActivoTrue("Pruebas");
        Restaurante restaurante = restauranteRepository.findTopByUserEquals(user);
        CartaRestaurante carta = cartaRestauranteRespository.findTopByRestauranteEquals(restaurante);
        List<Producto> productos = productoRepository.findByCartaResEqualsAndActivoTrue(carta);
        List<ListadoProductosDTO> listafinal = new ArrayList<>();
        for (Producto p : productos){
            List<ProductoFormatoDTO> formatos = productoFormatoMapper.toDTO(productoFormatoRepository.findByProductoEquals(p));
            ListadoProductosDTO nuevo = new ListadoProductosDTO();
            nuevo.setProducto(productoMapper.toDTO(p));
            nuevo.setFormatos(formatos);
            listafinal.add(nuevo);
        }
        return listafinal;

    }

}
