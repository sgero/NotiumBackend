package com.example.notiumb.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class EmailDTO {

    private String to;
    private String subject;
    private String text;


}
