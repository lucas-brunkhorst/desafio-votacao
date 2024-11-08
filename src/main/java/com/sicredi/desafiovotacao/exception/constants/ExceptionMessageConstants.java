package com.sicredi.desafiovotacao.exception.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ExceptionMessageConstants {

    public static final String PAUTA_NAO_ENCONTRADA = "Pauta não encontrada.";
    public static final String ASSOCIADO_NAO_ENCONTRADO = "Associado não encontrado.";
    public static final String SESSAO_ENCERRADA = "A sessão de votação está fechada para esta pauta.";
    public static final String ASSOCIADO_JA_VOTOU = "Associado já votou nesta pauta.";

    public static final String CPF_INVALIDO = "O CPF fornecido é inválido.";
    public static final String VOTO_NAO_AUTORIZADO = "O associado não está autorizado a votar.";
}
