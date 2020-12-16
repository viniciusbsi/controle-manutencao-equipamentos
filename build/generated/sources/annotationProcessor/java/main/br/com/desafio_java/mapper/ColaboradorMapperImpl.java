package br.com.desafio_java.mapper;

import br.com.desafio_java.dto.ColaboradorPostDto;
import br.com.desafio_java.model.Colaborador;
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
public class ColaboradorMapperImpl implements ColaboradorMapper {

    @Override
    public List<ColaboradorPostDto> toDTO(List<Colaborador> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<ColaboradorPostDto> list = new ArrayList<ColaboradorPostDto>( dtos.size() );
        for ( Colaborador colaborador : dtos ) {
            list.add( toDTO( colaborador ) );
        }

        return list;
    }

    @Override
    public List<Colaborador> toEntity(List<ColaboradorPostDto> entities) {
        if ( entities == null ) {
            return null;
        }

        List<Colaborador> list = new ArrayList<Colaborador>( entities.size() );
        for ( ColaboradorPostDto colaboradorPostDto : entities ) {
            list.add( toEntity( colaboradorPostDto ) );
        }

        return list;
    }

    @Override
    public ColaboradorPostDto toDTO(Colaborador entity) {
        if ( entity == null ) {
            return null;
        }

        ColaboradorPostDto colaboradorPostDto = new ColaboradorPostDto();

        colaboradorPostDto.nome = entity.nome;
        colaboradorPostDto.endereco = entity.endereco;
        colaboradorPostDto.telefone = entity.telefone;
        colaboradorPostDto.email = entity.email;
        colaboradorPostDto.setor = entity.setor;

        return colaboradorPostDto;
    }

    @Override
    public Colaborador toEntity(ColaboradorPostDto usuarioDTO) {
        if ( usuarioDTO == null ) {
            return null;
        }

        Colaborador colaborador = new Colaborador();

        colaborador.nome = usuarioDTO.nome;
        colaborador.endereco = usuarioDTO.endereco;
        colaborador.telefone = usuarioDTO.telefone;
        colaborador.email = usuarioDTO.email;
        colaborador.setor = usuarioDTO.setor;

        return colaborador;
    }
}
