package com.sicredi.desafiovotacao.dto;

import com.sicredi.desafiovotacao.model.enumeration.EnumVoto;
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
