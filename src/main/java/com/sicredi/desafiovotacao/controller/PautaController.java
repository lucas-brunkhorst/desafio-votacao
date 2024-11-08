package com.sicredi.desafiovotacao.controller;

import com.sicredi.desafiovotacao.dto.PautaDTO;
import com.sicredi.desafiovotacao.service.PautaServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pautas")
@RequiredArgsConstructor
@Slf4j
public class PautaController {

    private final PautaServiceImpl pautaServiceImpl;

    @PostMapping
    public ResponseEntity<PautaDTO> criarPauta(@RequestParam String titulo) {
        log.info("Recebendo solicitação para criar uma pauta com o título: {}", titulo);
        var pautaDTO = pautaServiceImpl.criarPauta(titulo);
        log.info("Pauta criada com sucesso, ID: {}", pautaDTO.getId());
        return new ResponseEntity<>(pautaDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{pautaId}/abrir-sessao")
    public ResponseEntity<PautaDTO> abrirSessao(@PathVariable Long pautaId, @RequestParam(required = false) Integer duracaoEmMinutos) {
        log.info("Recebendo solicitação para abrir sessão para pauta ID: {}", pautaId);
        var pautaDTO = pautaServiceImpl.abrirSessao(pautaId, duracaoEmMinutos);

        log.info("Sessão aberta para pauta ID: {} com fechamento em {}", pautaId, pautaDTO.getDataFechamento());
        return new ResponseEntity<>(pautaDTO, HttpStatus.OK);
    }

    @GetMapping("/{pautaId}/sessao-ativa")
    public ResponseEntity<Boolean> verificarSessaoAtiva(@PathVariable Long pautaId) {
        log.info("Verificando se a sessão está ativa para pauta ID: {}", pautaId);

        PautaDTO pautaDTO = pautaServiceImpl.buscarPautaDTO(pautaId);
        boolean isAtiva = pautaServiceImpl.isSessaoAtiva(pautaDTO);
        log.info("Sessão ativa para pauta ID: {}: {}", pautaId, isAtiva);
        return ResponseEntity.ok(isAtiva);
    }

    @GetMapping("/{pautaId}")
    public ResponseEntity<PautaDTO> buscarPauta(@PathVariable Long pautaId) {
        log.info("Buscando pauta com ID: {}", pautaId);

        PautaDTO pautaDTO = pautaServiceImpl.buscarPautaDTO(pautaId);
        return new ResponseEntity<>(pautaDTO, HttpStatus.OK);
    }

}
