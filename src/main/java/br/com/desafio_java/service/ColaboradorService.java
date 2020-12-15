package br.com.desafio_java.service;

import br.com.desafio_java.model.Colaborador;
import br.com.desafio_java.repository.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColaboradorService extends ServiceAbstrataImpl<ColaboradorRepository, Colaborador, Long> {

    @Autowired
    public ColaboradorService(ColaboradorRepository repository) {
        super(repository);
    }
}
