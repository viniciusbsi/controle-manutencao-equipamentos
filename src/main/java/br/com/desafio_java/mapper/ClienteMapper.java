package br.com.desafio_java.mapper;

import br.com.desafio_java.dto.ClientePostDto;
import br.com.desafio_java.model.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper extends EntidadeMapper<Cliente, ClientePostDto> {

    ClientePostDto toDTO(Cliente entity);

    @Override
    Cliente toEntity(ClientePostDto usuarioDTO);
}

