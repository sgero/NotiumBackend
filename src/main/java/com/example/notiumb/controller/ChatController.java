package com.example.notiumb.controller;

import com.example.notiumb.dto.ChatMensajeDTO;
import com.example.notiumb.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @GetMapping("/mensajes/{id}")
    public List<ChatMensajeDTO> listarMensajesByEvento(@PathVariable(value = "id") Integer idEvento){
        return chatService.getAll(idEvento);
    }

    @PostMapping("/guardar")
    public ChatMensajeDTO guardarMensaje(@RequestBody ChatMensajeDTO dto){
        return chatService.guardarMensaje(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){
        return chatService.delete(id);
    }

}
