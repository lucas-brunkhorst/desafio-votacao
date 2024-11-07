package com.example.desafiovotacao.sicredi.model;

import com.example.desafiovotacao.sicredi.model.enumeration.EnumVoto;
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


