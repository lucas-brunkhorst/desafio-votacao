package com.sicredi.desafiovotacao.model;

import com.sicredi.desafiovotacao.model.enumeration.EnumVoto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "votos")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "associado_id", nullable = false)
    private AssociadoEntity associado;

    @ManyToOne
    @JoinColumn(name = "pauta_id", nullable = false)
    private PautaEntity pauta;

    @Enumerated(EnumType.STRING)
    private EnumVoto voto;
}


