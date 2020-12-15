package br.com.desafio_java.service;

import br.com.desafio_java.model.Cliente;
import br.com.desafio_java.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService extends ServiceAbstrataImpl<ClienteRepository, Cliente, Long> {

    @Autowired
    public ClienteService(ClienteRepository repository) {
        super(repository);
    }
}
