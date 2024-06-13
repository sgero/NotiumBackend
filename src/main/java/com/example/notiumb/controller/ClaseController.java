package com.example.notiumb.controller;

import com.example.notiumb.dto.ClaseDTO;
import com.example.notiumb.service.ClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/clase")
public class ClaseController {

    @Autowired
    private ClaseService claseService;

    @GetMapping("/getAll")
    public List<ClaseDTO> getAll(){
        return claseService.getAll();
    }

}
