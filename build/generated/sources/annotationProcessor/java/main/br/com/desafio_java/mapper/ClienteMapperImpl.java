package br.com.desafio_java.mapper;

import br.com.desafio_java.dto.ClientePostDto;
import br.com.desafio_java.model.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-16T13:58:05-0300",
    comments = "version: 1.4.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.7.1.jar, environment: Java 11.0.9 (Oracle Corporation)"
)
@Component
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public List<ClientePostDto> toDTO(List<Cliente> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<ClientePostDto> list = new ArrayList<ClientePostDto>( dtos.size() );
        for ( Cliente cliente : dtos ) {
            list.add( toDTO( cliente ) );
        }

        return list;
    }

    @Override
    public List<Cliente> toEntity(List<ClientePostDto> entities) {
        if ( entities == null ) {
            return null;
        }

        List<Cliente> list = new ArrayList<Cliente>( entities.size() );
        for ( ClientePostDto clientePostDto : entities ) {
            list.add( toEntity( clientePostDto ) );
        }

        return list;
    }

    @Override
    public ClientePostDto toDTO(Cliente entity) {
        if ( entity == null ) {
            return null;
        }

        ClientePostDto clientePostDto = new ClientePostDto();

        clientePostDto.nome = entity.nome;
        clientePostDto.endereco = entity.endereco;
        clientePostDto.telefone = entity.telefone;
        clientePostDto.email = entity.email;

        return clientePostDto;
    }

    @Override
    public Cliente toEntity(ClientePostDto usuarioDTO) {
        if ( usuarioDTO == null ) {
            return null;
        }

        Cliente cliente = new Cliente();

        cliente.nome = usuarioDTO.nome;
        cliente.endereco = usuarioDTO.endereco;
        cliente.telefone = usuarioDTO.telefone;
        cliente.email = usuarioDTO.email;

        return cliente;
    }
}
