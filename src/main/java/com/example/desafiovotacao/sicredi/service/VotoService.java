package com.example.desafiovotacao.sicredi.service;

import com.example.desafiovotacao.sicredi.dto.VotoDTO;
import com.example.desafiovotacao.sicredi.exception.custom.BadRequestException;
import com.example.desafiovotacao.sicredi.model.AssociadoEntity;
import com.example.desafiovotacao.sicredi.model.PautaEntity;
import com.example.desafiovotacao.sicredi.model.VotoEntity;
import com.example.desafiovotacao.sicredi.model.enumeration.EnumVoto;
import com.example.desafiovotacao.sicredi.repository.AssociadoRepositoryImpl;
import com.example.desafiovotacao.sicredi.repository.PautaRepositoryImpl;
import com.example.desafiovotacao.sicredi.repository.VotoRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.desafiovotacao.sicredi.exception.constants.ExceptionMessageConstants.*;

@Service
@RequiredArgsConstructor
public class VotoService {

    private final PautaRepositoryImpl pautaRepository;
    private final AssociadoRepositoryImpl associadoRepository;
    private final VotoRepositoryImpl votoRepository;
    private final ModelMapper modelMapper;

    public VotoDTO registrarVoto(Long pautaId, Long associadoId, String voto) {
        PautaEntity pauta = pautaRepository.findById(pautaId)
                .orElseThrow(() -> new BadRequestException(PAUTA_NAO_ENCONTRADA));

        AssociadoEntity associado = associadoRepository.findById(associadoId)
                .orElseThrow(() -> new BadRequestException(ASSOCIADO_NAO_ENCONTRADO));

        if (!isSessaoAtiva(pauta)) {
            throw new BadRequestException(SESSAO_ENCERRADA);
        }

        boolean jaVotou = votoRepository.existsByPautaAndAssociado(pauta, associado);
        if (jaVotou) {
            throw new BadRequestException(ASSOCIADO_JA_VOTOU);
        }

        EnumVoto votoEnum;
        try {
            votoEnum = EnumVoto.valueOf(voto.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Voto inválido! O valor permitido é 'SIM' ou 'NAO'.");
        }

        var votoEntity = VotoEntity.builder()
                .pauta(pauta)
                .associado(associado)
                .voto(votoEnum)
                .build();

        var savedVoto = votoRepository.save(votoEntity);

        return modelMapper.map(savedVoto, VotoDTO.class);
    }

    public boolean isSessaoAtiva(PautaEntity pauta) {
        return pauta.getDataAbertura() != null &&
                pauta.getDataFechamento() != null &&
                LocalDateTime.now().isBefore(pauta.getDataFechamento());
    }

    public String contabilizarVotos(Long pautaId) {
        PautaEntity pauta = pautaRepository.findById(pautaId)
                .orElseThrow(() -> new BadRequestException(PAUTA_NAO_ENCONTRADA));

        if (isSessaoAtiva(pauta)) {
            throw new BadRequestException(SESSAO_ENCERRADA);
        }

        List<VotoEntity> votos = votoRepository.findByPauta(pauta);

        long votosSim = votos.stream().filter(v -> v.getVoto() == EnumVoto.SIM).count();
        long votosNao = votos.stream().filter(v -> v.getVoto() == EnumVoto.NAO).count();

        return String.format("Votos SIM: %d, Votos NÃO: %d", votosSim, votosNao);
    }

    public List<VotoDTO> listarVotos(Long pautaId) {
        PautaEntity pauta = pautaRepository.findById(pautaId)
                .orElseThrow(() -> new BadRequestException(PAUTA_NAO_ENCONTRADA));

        List<VotoEntity> votos = votoRepository.findByPauta(pauta);

        return votos.stream()
                .map(voto -> modelMapper.map(voto, VotoDTO.class))
                .collect(Collectors.toList());
    }
}
