package br.com.desafio_java.mapper;

import br.com.desafio_java.dto.EquipamentoPostDto;
import br.com.desafio_java.model.Equipamento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EquipamentoMapper extends EntidadeMapper<Equipamento, EquipamentoPostDto> {

    EquipamentoPostDto toDTO(Equipamento entity);

    @Override
    Equipamento toEntity(EquipamentoPostDto usuarioDTO);
}

