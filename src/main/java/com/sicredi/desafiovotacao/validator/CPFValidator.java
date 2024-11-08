package com.sicredi.desafiovotacao.validator;

import com.sicredi.desafiovotacao.exception.custom.BadRequestException;
import com.sicredi.desafiovotacao.model.AssociadoEntity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.sicredi.desafiovotacao.exception.constants.ExceptionMessageConstants.CPF_INVALIDO;

public class CPFValidator {

    private static final String CPF_REGEX = "^[0-9]{11}$";

    public static void validarCPF(String cpf) {
        Pattern pattern = Pattern.compile(CPF_REGEX);
        Matcher matcher = pattern.matcher(cpf);
        if (!matcher.matches()) {
            throw new BadRequestException(CPF_INVALIDO);
        }
    }

    public static void verificarElegibilidadeParaVoto(AssociadoEntity associado) {
        if (associado.getCpf().startsWith("000") || associado.getCpf().startsWith("111")) {
            throw new BadRequestException("Candidato nao esta apto para votar");
        }
    }
}
