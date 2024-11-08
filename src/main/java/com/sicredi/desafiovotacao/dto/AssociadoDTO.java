package com.sicredi.desafiovotacao.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssociadoDTO {

    private Long id;
    private String cpf;
    private String nome;
}
