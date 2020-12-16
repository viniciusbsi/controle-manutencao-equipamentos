package br.com.desafio_java.mapper;

import br.com.desafio_java.dto.EquipamentoPostDto;
import br.com.desafio_java.model.Equipamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface EquipamentoMapper extends EntidadeMapper<Equipamento, EquipamentoPostDto> {

    EquipamentoPostDto toDTO(Equipamento entity);

    @Override
    @Mappings({
            @Mapping(target = "id", ignore = true),
    })
    Equipamento toEntity(EquipamentoPostDto usuarioDTO);
}

