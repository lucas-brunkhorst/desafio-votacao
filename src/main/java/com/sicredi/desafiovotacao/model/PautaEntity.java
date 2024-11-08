package com.sicredi.desafiovotacao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pautas")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PautaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Título da pauta não pode ser vazio")
    private String titulo;

    private LocalDateTime dataAbertura;

    private LocalDateTime dataFechamento;

    @OneToMany(mappedBy = "pauta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VotoEntity> votos;

}
