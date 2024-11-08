package com.sicredi.desafiovotacao.controller;

import com.sicredi.desafiovotacao.dto.VotoDTO;
import com.sicredi.desafiovotacao.service.VotoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/votos")
@RequiredArgsConstructor
public class VotoController {

    private final VotoServiceImpl votoServiceImpl;

    @PostMapping("/{pautaId}")
    public ResponseEntity<VotoDTO> registrarVoto(
            VotoDTO dto) {

        var votoRegistrado = votoServiceImpl.registrarVoto(dto);

        return ResponseEntity.status(CREATED).body(votoRegistrado);
    }
}
