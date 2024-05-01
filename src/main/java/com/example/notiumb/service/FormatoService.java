package com.example.notiumb.service;

import com.example.notiumb.converter.IFormatoMapper;
import com.example.notiumb.repository.IFormatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormatoService {

    @Autowired
    private IFormatoRepository iFormatoRepository;

    @Autowired
    private IFormatoMapper iFormatoMapper;


}
