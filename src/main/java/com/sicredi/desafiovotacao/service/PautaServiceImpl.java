package com.sicredi.desafiovotacao.service;

import com.sicredi.desafiovotacao.dto.PautaDTO;
import com.sicredi.desafiovotacao.exception.custom.BadRequestException;
import com.sicredi.desafiovotacao.model.PautaEntity;
import com.sicredi.desafiovotacao.repository.PautaRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.sicredi.desafiovotacao.exception.constants.ExceptionMessageConstants.PAUTA_NAO_ENCONTRADA;

@Service
@RequiredArgsConstructor
@Slf4j
public class PautaServiceImpl {

    private final PautaRepositoryImpl pautaRepository;
    private final ModelMapper modelMapper;

    public PautaDTO criarPauta(String titulo) {
        log.info("Criando nova pauta com título: {}", titulo);
        var pauta = PautaEntity.builder()
                .titulo(titulo)
                .dataAbertura(LocalDateTime.now())
                .dataFechamento(LocalDateTime.now().plusMinutes(1))
                .build();

        var savedPauta = pautaRepository.save(pauta);
        log.info("Pauta criada com sucesso: {}", savedPauta.getId());

        return modelMapper.map(savedPauta, PautaDTO.class);
    }

    public PautaDTO abrirSessao(Long pautaId, Integer duracaoEmMinutos) {
        log.info("Abrindo sessão de votação para pauta ID: {}", pautaId);

        var pauta = buscarPautaEntity(pautaId);
        pauta.setDataAbertura(LocalDateTime.now());
        pauta.setDataFechamento(LocalDateTime.now().plusMinutes(duracaoEmMinutos != null ? duracaoEmMinutos : 1));
        var updatedPauta = pautaRepository.save(pauta);

        log.info("Sessão aberta para pauta ID: {} com fechamento em {}", pautaId, updatedPauta.getDataFechamento());
        return modelMapper.map(updatedPauta, PautaDTO.class);
    }

    public PautaDTO buscarPautaDTO(Long pautaId) {
        PautaEntity pauta = buscarPautaEntity(pautaId);
        return modelMapper.map(pauta, PautaDTO.class);
    }

    public PautaEntity buscarPautaEntity(Long pautaId) {
        log.debug("Buscando pauta com ID: {}", pautaId);
        return pautaRepository.findById(pautaId)
                .orElseThrow(() -> {
                    log.error("Pauta com ID {} não encontrada", pautaId);
                    return new BadRequestException(PAUTA_NAO_ENCONTRADA);
                });
    }

    public boolean isSessaoAtiva(PautaDTO pautaDTO) {
        log.info("Verificando se a sessão está ativa para pauta ID: {}", pautaDTO.getId());
        return pautaDTO.getDataAbertura() != null &&
                pautaDTO.getDataFechamento() != null &&
                LocalDateTime.now().isBefore(pautaDTO.getDataFechamento());
    }
}
