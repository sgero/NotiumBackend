package com.example.notiumb.service;

import com.example.notiumb.converter.IChatMensajeConverter;
import com.example.notiumb.converter.IOcioNocturnoMapper;
import com.example.notiumb.dto.ChatMensajeDTO;
import com.example.notiumb.model.ChatMensaje;
import com.example.notiumb.model.Cliente;
import com.example.notiumb.model.OcioNocturno;
import com.example.notiumb.repository.IChatMensajeRepository;
import com.example.notiumb.repository.IClienteRepository;
import com.example.notiumb.repository.IOcioNocturnoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.util.StringUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ChatService {
    @Autowired
    private IChatMensajeRepository chatMensajeRepository;
    @Autowired
    private IClienteRepository clienteRepository;
    @Autowired
    private IOcioNocturnoRepository ocioNocturnoRepository;
    @Autowired
    private IChatMensajeConverter converter;
    @Autowired
    private IOcioNocturnoMapper ocioNocturnoMapper;


    public List<ChatMensajeDTO> getAll(Integer idOcio) {
        List<ChatMensaje> chatMensajes = chatMensajeRepository.findAllByChat_IdOrderByFechaDesc(idOcio);
        return converter.toDTO(chatMensajes);
    }

    @Transactional
    public ChatMensajeDTO guardarMensaje(ChatMensajeDTO dto) {
        if (dto != null && dto.getChatDTO() != null && dto.getChatDTO().getId() != null && !StringUtils.isEmpty(dto.getTexto())) {
            dto.setFecha(Timestamp.valueOf(LocalDateTime.now()));
            //Verificamos si es editar o crear
            dto.setEditado(dto.getId() != null);
            ChatMensaje chatMensajeGuardar = converter.toEntity(dto);
            OcioNocturno ocioNocturno = ocioNocturnoRepository.findTopByIdAndActivoIsTrue(dto.getChatDTO().getId());
            if (ocioNocturno != null) {
                chatMensajeGuardar.setChat(ocioNocturno);
            }
            chatMensajeRepository.save(chatMensajeGuardar);
            return converter.toDTO(chatMensajeGuardar);
        }
        return null;
    }

    @Transactional
    public ResponseEntity<Void> delete(Integer id) {
        if (id != null && id > 0) {
            Optional<ChatMensaje> chatMensajeDelete = chatMensajeRepository.findById(Long.valueOf(id));
            chatMensajeDelete.ifPresent(chatMensaje -> chatMensajeRepository.delete(chatMensaje));
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<Void> actualizar(Integer idCliente, Integer idChat) {
        if (idChat != null && idChat > 0 && idCliente != null && idCliente > 0) {
            Cliente cliente = clienteRepository.findByIdAndActivoIsTrue(idCliente).orElse(null);
            OcioNocturno chat = ocioNocturnoRepository.findByIdAndActivoIsTrue(idChat).orElse(null);
            if (cliente != null && chat != null && cliente.getId() != null) {
                Set<OcioNocturno> chatsCliente = ocioNocturnoRepository.getChatsCliente(cliente.getId());
                if (!CollectionUtils.isEmpty(chatsCliente)) {
                    if (chatsCliente.contains(chat)) {
                        chatsCliente.remove(chat);
                        cliente.setChatsCliente(chatsCliente);
                    } else {
                        chatsCliente.add(chat);
                        cliente.setChatsCliente(chatsCliente);
                    }
                } else {
                    chatsCliente.add(chat);
                    cliente.setChatsCliente(chatsCliente);
                }
                clienteRepository.save(cliente);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public boolean verificarClienteEnChat(Integer idCliente, Integer idChat) {
        Integer result = clienteRepository.countClienteEnChat(idCliente, idChat);
        return result != 0;
    }

}
