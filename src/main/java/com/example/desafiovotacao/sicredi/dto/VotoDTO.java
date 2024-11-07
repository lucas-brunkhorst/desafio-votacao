package com.example.desafiovotacao.sicredi.dto;

import com.example.desafiovotacao.sicredi.model.enumeration.EnumVoto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VotoDTO {

    private Long id;
    private Long pautaId;
    private Long associadoId;
    private EnumVoto voto;
}
