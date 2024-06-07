package com.example.notiumb.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatClienteDTO {
    private OcioNocturnoDTO chat;
    private ClienteDTO cliente;
}
