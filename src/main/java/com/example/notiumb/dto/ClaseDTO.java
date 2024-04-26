package com.example.notiumb.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class ClaseDTO {

    private Integer id;
    @NotBlank
    private Integer clase;




}
