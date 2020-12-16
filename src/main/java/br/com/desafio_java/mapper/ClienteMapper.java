package br.com.desafio_java.mapper;

import br.com.desafio_java.dto.ClientePostDto;
import br.com.desafio_java.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ClienteMapper extends EntidadeMapper<Cliente, ClientePostDto> {

    ClientePostDto toDTO(Cliente entity);

    @Override
    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    Cliente toEntity(ClientePostDto usuarioDTO);
}

