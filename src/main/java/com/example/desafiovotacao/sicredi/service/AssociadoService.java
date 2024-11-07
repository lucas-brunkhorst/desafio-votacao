package com.example.desafiovotacao.sicredi.service;

import com.example.desafiovotacao.sicredi.exception.custom.BadRequestException;
import com.example.desafiovotacao.sicredi.model.AssociadoEntity;
import com.example.desafiovotacao.sicredi.repository.AssociadoRepositoryImpl;
import com.example.desafiovotacao.sicredi.validator.CPFValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.desafiovotacao.sicredi.exception.constants.ExceptionMessageConstants.ASSOCIADO_NAO_ENCONTRADO;

@Service
@RequiredArgsConstructor
public class AssociadoService {

    private final AssociadoRepositoryImpl associadoRepository;

    public AssociadoEntity cadastrarAssociado(AssociadoEntity associado) {
        CPFValidator.validarCPF(associado.getCpf());
        CPFValidator.verificarElegibilidadeParaVoto(associado);

        return associadoRepository.save(associado);
    }

    public AssociadoEntity buscarAssociado(Long associadoId) {
        return associadoRepository.findById(associadoId)
                .orElseThrow(() -> new BadRequestException(ASSOCIADO_NAO_ENCONTRADO));
    }
}
