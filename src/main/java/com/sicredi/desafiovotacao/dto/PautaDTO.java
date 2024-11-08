package com.sicredi.desafiovotacao.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PautaDTO {

    private Long id;
    private String titulo;
    private LocalDateTime dataAbertura;
    private LocalDateTime dataFechamento;
}
