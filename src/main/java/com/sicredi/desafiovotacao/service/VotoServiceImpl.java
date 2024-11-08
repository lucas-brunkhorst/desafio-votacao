package com.sicredi.desafiovotacao.service;

import com.sicredi.desafiovotacao.dto.VotoDTO;
import com.sicredi.desafiovotacao.exception.custom.BadRequestException;
import com.sicredi.desafiovotacao.model.AssociadoEntity;
import com.sicredi.desafiovotacao.model.PautaEntity;
import com.sicredi.desafiovotacao.model.VotoEntity;
import com.sicredi.desafiovotacao.model.enumeration.EnumVoto;
import com.sicredi.desafiovotacao.repository.AssociadoRepositoryImpl;
import com.sicredi.desafiovotacao.repository.PautaRepositoryImpl;
import com.sicredi.desafiovotacao.repository.VotoRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.sicredi.desafiovotacao.exception.constants.ExceptionMessageConstants.*;

@Service
@RequiredArgsConstructor
public class VotoServiceImpl {

    private final PautaRepositoryImpl pautaRepository;
    private final AssociadoRepositoryImpl associadoRepository;
    private final VotoRepositoryImpl votoRepository;
    private final ModelMapper modelMapper;

    public VotoDTO registrarVoto(VotoDTO dto) {
        PautaEntity pauta = pautaRepository.findById(dto.getPautaId())
                .orElseThrow(() -> new BadRequestException(PAUTA_NAO_ENCONTRADA));

        AssociadoEntity associado = associadoRepository.findById(dto.getAssociadoId())
                .orElseThrow(() -> new BadRequestException(ASSOCIADO_NAO_ENCONTRADO));

        if (!isSessaoAtiva(pauta)) {
            throw new BadRequestException(SESSAO_ENCERRADA);
        }

        boolean jaVotou = votoRepository.existsByPautaAndAssociado(pauta, associado);
        if (jaVotou) {
            throw new BadRequestException(ASSOCIADO_JA_VOTOU);
        }

        var votoEntity = VotoEntity.builder()
                .pauta(pauta)
                .associado(associado)
                .voto(dto.getVoto())
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
