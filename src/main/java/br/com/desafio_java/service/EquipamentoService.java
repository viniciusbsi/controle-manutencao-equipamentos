package br.com.desafio_java.service;

import br.com.desafio_java.model.Equipamento;
import br.com.desafio_java.repository.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipamentoService extends ServiceAbstrataImpl<EquipamentoRepository, Equipamento, Long> {

    @Autowired
    public EquipamentoService(EquipamentoRepository repository) {
        super(repository);
    }
}
