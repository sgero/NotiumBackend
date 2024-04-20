package com.example.notiumb.service;

import com.example.notiumb.converter.IRppMapper;
import com.example.notiumb.repository.IRppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RppService {

    @Autowired
    private IRppRepository repository;
    @Autowired
    private IRppMapper converter;
}
