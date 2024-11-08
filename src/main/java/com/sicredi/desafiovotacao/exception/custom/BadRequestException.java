package com.sicredi.desafiovotacao.exception.custom;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String mensagem) {
        super(mensagem);
    }
}
