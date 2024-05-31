package com.example.notiumb.service;


import com.example.notiumb.converter.IProductoFormatoMapper;
import com.example.notiumb.dto.CartaRestauranteDTO;
import com.example.notiumb.dto.ProductoAuxDTO;
import com.example.notiumb.dto.ProductoDTO;
import com.example.notiumb.dto.ProductoFormatoDTO;
import com.example.notiumb.model.*;
import com.example.notiumb.model.enums.TipoCategoria;
import com.example.notiumb.repository.IFormatoRepository;
import com.example.notiumb.repository.IProductoFormatoRepository;
import com.example.notiumb.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}