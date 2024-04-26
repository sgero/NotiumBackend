package com.example.notiumb.service;


import com.example.notiumb.converter.IProductoFormatoMapper;
import com.example.notiumb.repository.IProductoFormatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoFormatoService {

    @Autowired
    private IProductoFormatoRepository iProductoFormatoRepository;

    @Autowired
    private IProductoFormatoMapper iProductoFormatoMapper;
}
