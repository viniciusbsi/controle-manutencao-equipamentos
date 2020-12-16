package br.com.desafio_java.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="A ordem de serviço não pode ser finalizada")
public class ErroFinalizacaoOrdemServicoException extends RuntimeException {

    public ErroFinalizacaoOrdemServicoException(String message) {
        super(message);
    }
}
