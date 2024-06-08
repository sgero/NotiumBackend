package com.example.notiumb.service;

import com.example.notiumb.converter.IChatMensajeConverter;
import com.example.notiumb.converter.IOcioNocturnoMapper;
import com.example.notiumb.dto.ChatMensajeDTO;
import com.example.notiumb.model.ChatMensaje;
import com.example.notiumb.model.Cliente;
import com.example.notiumb.model.Evento;
import com.example.notiumb.model.OcioNocturno;
import com.example.notiumb.repository.IChatMensajeRepository;
import com.example.notiumb.repository.IClienteRepository;
import com.example.notiumb.repository.IEventoRepository;
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
    @Autowired
    private IEventoRepository eventoRepository;


    public List<ChatMensajeDTO> getAll(Integer idEvento) {
        List<ChatMensaje> chatMensajes = chatMensajeRepository.findAllByChatEvento_IdOrderByFechaDesc(idEvento);
        return converter.toDTO(chatMensajes);
    }

    @Transactional
    public ChatMensajeDTO guardarMensaje(ChatMensajeDTO dto) {
        if (dto != null && dto.getChatEventoDTO() != null && dto.getChatEventoDTO().getId() != null && !StringUtils.isEmpty(dto.getTexto())) {
            dto.setFecha(Timestamp.valueOf(LocalDateTime.now()));
            //Verificamos si es editar o crear
            dto.setEditado(dto.getId() != null);
            ChatMensaje chatMensajeGuardar = converter.toEntity(dto);
            Evento evento = eventoRepository.findTopByIdAndActivoIsTrue(dto.getChatEventoDTO().getId());
            if (evento != null) {
                chatMensajeGuardar.setChatEvento(evento);
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

}
