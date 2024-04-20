package com.example.notiumb.service;


import com.example.notiumb.converter.ProductoTipoPlatoMapper;
import com.example.notiumb.repository.IProductoTipoPlatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoTipoPlatoService {

    @Autowired
    private IProductoTipoPlatoRepository productoTipoPlatoRepository;

    @Autowired
    private ProductoTipoPlatoMapper productoTipoPlatoMapper;
}
