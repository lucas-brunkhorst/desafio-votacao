package com.sicredi.desafiovotacao.service;

import com.sicredi.desafiovotacao.exception.custom.BadRequestException;
import com.sicredi.desafiovotacao.model.AssociadoEntity;
import com.sicredi.desafiovotacao.repository.AssociadoRepositoryImpl;
import com.sicredi.desafiovotacao.validator.CPFValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.sicredi.desafiovotacao.exception.constants.ExceptionMessageConstants.ASSOCIADO_NAO_ENCONTRADO;

@Service
@RequiredArgsConstructor
public class AssociadoServiceImpl {

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
