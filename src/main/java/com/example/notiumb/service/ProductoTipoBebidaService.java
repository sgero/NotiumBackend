package com.example.notiumb.service;

import com.example.notiumb.converter.ProductoTipoBebidaMapper;
import com.example.notiumb.repository.IProductoTipoBebidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoTipoBebidaService {

    @Autowired
    private IProductoTipoBebidaRepository productoTipoBebidaRepository;

    @Autowired
    private ProductoTipoBebidaMapper productoTipoBebidaMapper;


}
