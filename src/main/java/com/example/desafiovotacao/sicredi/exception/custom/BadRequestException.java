package com.example.desafiovotacao.sicredi.exception.custom;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String mensagem) {
        super(mensagem);
    }
}
