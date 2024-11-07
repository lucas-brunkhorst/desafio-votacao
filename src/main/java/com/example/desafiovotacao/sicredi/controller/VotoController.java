package com.example.desafiovotacao.sicredi.controller;

import com.example.desafiovotacao.sicredi.dto.VotoDTO;
import com.example.desafiovotacao.sicredi.service.VotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/votos")
@RequiredArgsConstructor
public class VotoController {

    private final VotoService votoService;

    @PostMapping("/{pautaId}")
    public ResponseEntity<VotoDTO> registrarVoto(
            @PathVariable Long pautaId,
            @RequestParam Long associadoId,
            @RequestParam String voto) {

        var votoRegistrado = votoService.registrarVoto(pautaId, associadoId, voto);

        return ResponseEntity.status(CREATED).body(votoRegistrado);
    }
}
