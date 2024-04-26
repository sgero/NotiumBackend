package com.example.notiumb.service;

import com.example.notiumb.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    @Autowired
    private IProductoRepository iproductoRepository;
}
