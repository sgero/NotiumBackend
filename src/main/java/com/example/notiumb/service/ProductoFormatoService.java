package com.example.notiumb.service;


import com.example.notiumb.converter.IProductoFormatoMapper;
import com.example.notiumb.dto.*;
import com.example.notiumb.model.*;
import com.example.notiumb.model.enums.Rol;
import com.example.notiumb.model.enums.TipoCategoria;
import com.example.notiumb.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductoFormatoService {

    @Autowired
    private IProductoFormatoRepository iProductoFormatoRepository;

    @Autowired
    private IProductoFormatoMapper iProductoFormatoMapper;

    @Autowired
    private IProductoRepository iProductoRepository;

    @Autowired
    private IFormatoRepository iFormatoRepository;
    @Autowired
    private IOcioNocturnoRepository ocioNocturnoRepository;
    @Autowired
    private ICartaOcioRepository cartaOcioRepository;
    @Autowired
    private IUserRepository userRepository;

    public ProductoFormatoDTO crearProductoFormato(ProductoFormatoDTO productoFormatoDTO) {

        Producto producto = iProductoRepository.findTopById(productoFormatoDTO.getProductoDTO().getId());
        Formato formato = iFormatoRepository.findTopById(productoFormatoDTO.getFormatoDTO().getId());
        List<ProductoFormato> productoIgual = iProductoFormatoRepository.findByProductoEqualsAndFormatoEquals(producto,formato);

        ProductoFormatoDTO productoFormatoDTONew = new ProductoFormatoDTO();
        productoFormatoDTONew.setPrecio(productoFormatoDTO.getPrecio());
        productoFormatoDTONew.setProductoDTO(productoFormatoDTO.getProductoDTO());
        productoFormatoDTONew.setFormatoDTO(productoFormatoDTO.getFormatoDTO());

        if (productoIgual.size() > 0) {
            ProductoFormato productoFormatoExistente = productoIgual.get(0);
            productoFormatoExistente.setPrecio(productoFormatoDTO.getPrecio());

            return iProductoFormatoMapper.toDTO(iProductoFormatoRepository.save(productoFormatoExistente));
        } else {
            return iProductoFormatoMapper.toDTO(iProductoFormatoRepository.save(iProductoFormatoMapper.toEntity(productoFormatoDTONew)));
        }
    }

    public List<ProductoFormatoDTO> listarByFormato(User user) {

        List<ProductoFormato> productoFormatos = new ArrayList<>();

        List<ProductoFormato> formatos = null;

        if (user.getRol() == Rol.OCIONOCTURNO) {
            OcioNocturno ocioNocturno = ocioNocturnoRepository.findByUserEqualsAndActivoIsTrue(user);
            CartaOcio cartaOcio = cartaOcioRepository.findTopByOcioNocturnoEqualsAndActivoIsTrue(ocioNocturno);

            List<Producto> listaProductos = iProductoRepository.findByCartaOcioEqualsAndActivoTrue(cartaOcio);
            for (Producto producto : listaProductos) {
                formatos = iProductoFormatoRepository.findByProductoEquals(producto);
                productoFormatos.addAll(formatos);
            }

//        }else if (user.getRol()== Rol.RESTAURANTE) {
//            Restaurante restaurante = restauranteRepository.findTopByUserEquals(user);
//            CartaRestaurante cartaRestaurante = cartaRestauranteRespository.findTopByRestauranteEquals(restaurante);
//
//            productos = productoMapper.toDTO(productoRepository.findByCartaResEqualsAndActivoTrue(cartaRestaurante));
//        }

        }
        return iProductoFormatoMapper.toDTO(productoFormatos);
    }

    public List<ProductoFormatoDTO> listarByFormatoCliente(TokenDTO tokenDTO) {

        List<ProductoFormato> productoFormatos = new ArrayList<>();
        List<ProductoFormato> formatos = null;
        User user = userRepository.findTopByUsernameAndActivoTrue(tokenDTO.getToken());

        if (user.getRol() == Rol.OCIONOCTURNO) {
            OcioNocturno ocioNocturno = ocioNocturnoRepository.findByUserEqualsAndActivoIsTrue(user);
            CartaOcio cartaOcio = cartaOcioRepository.findTopByOcioNocturnoEqualsAndActivoIsTrue(ocioNocturno);

            List<Producto> listaProductos = iProductoRepository.findByCartaOcioEqualsAndActivoTrue(cartaOcio);
            for (Producto producto : listaProductos) {
                formatos = iProductoFormatoRepository.findByProductoEquals(producto);
                productoFormatos.addAll(formatos);
            }

//        }else if (user.getRol()== Rol.RESTAURANTE) {
//            Restaurante restaurante = restauranteRepository.findTopByUserEquals(user);
//            CartaRestaurante cartaRestaurante = cartaRestauranteRespository.findTopByRestauranteEquals(restaurante);
//
//            productos = productoMapper.toDTO(productoRepository.findByCartaResEqualsAndActivoTrue(cartaRestaurante));
//        }

        }
        return iProductoFormatoMapper.toDTO(productoFormatos);
    }



}