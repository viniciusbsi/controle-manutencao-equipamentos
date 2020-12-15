package br.com.desafio_java.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Erro de sistema")
public class ErroSistemaException extends RuntimeException {

    public ErroSistemaException(String message) {
        super(message);
    }
}
