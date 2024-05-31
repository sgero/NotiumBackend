package com.example.notiumb.service;


import com.example.notiumb.converter.ICartaOcioMapper;
import com.example.notiumb.converter.ICartaRestauranteMapper;
import com.example.notiumb.converter.IProductoMapper;
import com.example.notiumb.converter.IRestauranteMapper;
import com.example.notiumb.dto.CartaRestauranteDTO;
import com.example.notiumb.dto.ProductoAuxDTO;
import com.example.notiumb.dto.ProductoDTO;
import com.example.notiumb.dto.TokenDTO;
import com.example.notiumb.model.*;
import com.example.notiumb.model.enums.Rol;
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
    private ICartaRestauranteMapper cartaRestauranteMapper;
    @Autowired
    private IOcioNocturnoRepository ocioNocturnoRepository;
    @Autowired
    private ICartaOcioRepository cartaOcioRepository;
    @Autowired
    private ICartaOcioMapper cartaOcioMapper;

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


    public ProductoDTO guardarProducto(ProductoAuxDTO productoAuxDTO, User user) {

//        String token = productoAuxDTO.getUsername();
        //String username = jwtService.extractUsername(productoDTO.getUsername());
//        User user = userRepository.findTopByUsernameAndActivoTrue(token);
        Producto producto = new Producto();
        if (user.getRol()== Rol.OCIONOCTURNO){
            OcioNocturno ocioNocturno = ocioNocturnoRepository.findByUserEqualsAndActivoIsTrue(user);
            CartaOcio cartaOcio = cartaOcioRepository.findTopByOcioNocturnoEqualsAndActivoIsTrue(ocioNocturno);
            producto.setNombre(productoAuxDTO.getNombre());
            producto.setTipoCategoria(TipoCategoria.valueOf(productoAuxDTO.getTipoCategoria()));
            producto.setCartaOcio(cartaOcio);
        }else if (user.getRol()== Rol.RESTAURANTE){
            Restaurante restaurante = restauranteRepository.findTopByUserEquals(user);
            CartaRestaurante cartaRestaurante = cartaRestauranteRespository.findTopByRestauranteEquals(restaurante);
            producto.setNombre(productoAuxDTO.getNombre());
            producto.setTipoCategoria(TipoCategoria.valueOf(productoAuxDTO.getTipoCategoria()));
            producto.setCartaRes(cartaRestaurante);
        }
        return productoMapper.toDTO(productoRepository.save(producto));

    }

    public List<ProductoDTO> listarProducto(TokenDTO tokenDTO) {
        String tokensio = tokenDTO.getToken();
        //String username = jwtService.extractUsername(tokenDTO.getToken());
        User user = userRepository.findTopByUsernameAndActivoTrue("Pruebas");
        Restaurante restaurante = restauranteRepository.findTopByUserEquals(user);
        CartaRestaurante carta = cartaRestauranteRespository.findTopByRestauranteEquals(restaurante);

        return productoMapper.toDTO(productoRepository.findByCartaResEqualsAndActivoTrue(carta));
    }

    public List<ProductoDTO> listarByProducto(User user) {

        List<ProductoDTO> productos = new ArrayList<>();

        if (user.getRol()== Rol.OCIONOCTURNO){
            OcioNocturno ocioNocturno = ocioNocturnoRepository.findByUserEqualsAndActivoIsTrue(user);
            CartaOcio cartaOcio = cartaOcioRepository.findTopByOcioNocturnoEqualsAndActivoIsTrue(ocioNocturno);

            productos =  productoMapper.toDTO(productoRepository.findByCartaOcioEqualsAndActivoTrue(cartaOcio));

        }else if (user.getRol()== Rol.RESTAURANTE) {
            Restaurante restaurante = restauranteRepository.findTopByUserEquals(user);
            CartaRestaurante cartaRestaurante = cartaRestauranteRespository.findTopByRestauranteEquals(restaurante);

            productos = productoMapper.toDTO(productoRepository.findByCartaResEqualsAndActivoTrue(cartaRestaurante));
        }
        return productos;
    }

}
