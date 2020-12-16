package br.com.desafio_java.mapper;

import br.com.desafio_java.dto.EquipamentoPostDto;
import br.com.desafio_java.model.Equipamento;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-16T11:53:20-0300",
    comments = "version: 1.4.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.7.1.jar, environment: Java 11.0.9 (Oracle Corporation)"
)
@Component
public class EquipamentoMapperImpl implements EquipamentoMapper {

    @Override
    public List<EquipamentoPostDto> toDTO(List<Equipamento> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<EquipamentoPostDto> list = new ArrayList<EquipamentoPostDto>( dtos.size() );
        for ( Equipamento equipamento : dtos ) {
            list.add( toDTO( equipamento ) );
        }

        return list;
    }

    @Override
    public List<Equipamento> toEntity(List<EquipamentoPostDto> entities) {
        if ( entities == null ) {
            return null;
        }

        List<Equipamento> list = new ArrayList<Equipamento>( entities.size() );
        for ( EquipamentoPostDto equipamentoPostDto : entities ) {
            list.add( toEntity( equipamentoPostDto ) );
        }

        return list;
    }

    @Override
    public EquipamentoPostDto toDTO(Equipamento entity) {
        if ( entity == null ) {
            return null;
        }

        EquipamentoPostDto equipamentoPostDto = new EquipamentoPostDto();

        equipamentoPostDto.nome = entity.nome;
        equipamentoPostDto.tipo = entity.tipo;
        equipamentoPostDto.marca = entity.marca;

        return equipamentoPostDto;
    }

    @Override
    public Equipamento toEntity(EquipamentoPostDto usuarioDTO) {
        if ( usuarioDTO == null ) {
            return null;
        }

        Equipamento equipamento = new Equipamento();

        equipamento.nome = usuarioDTO.nome;
        equipamento.tipo = usuarioDTO.tipo;
        equipamento.marca = usuarioDTO.marca;

        return equipamento;
    }
}
