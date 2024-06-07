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
    public List<ChatMensajeDTO> listarMensajesByOcio(@PathVariable(value = "id") Integer idOcio){
        return chatService.getAll(idOcio);
    }

    @GetMapping("/{idCliente}/{idChat}")
    public boolean verificarClienteEnChat(@PathVariable(value = "idCliente") Integer idCliente, @PathVariable(value = "idChat") Integer idChat){
        return chatService.verificarClienteEnChat(idCliente, idChat);
    }

    @PostMapping("/guardar")
    public ChatMensajeDTO guardarMensaje(@RequestBody ChatMensajeDTO dto){
        return chatService.guardarMensaje(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){
        return chatService.delete(id);
    }

    @PostMapping("/actualizar/{idCliente}/{idChat}")
    public  ResponseEntity<Void> actualizar(@PathVariable Integer idCliente, @PathVariable Integer idChat){
        return chatService.actualizar(idCliente, idChat);
    }

}
