package br.com.desafio_java.mapper;

import java.util.List;

public interface EntidadeMapper<T, DTO> {

    DTO toDTO(T entity);

    T toEntity(DTO dto);

    List<DTO> toDTO(List<T> dtos);

    List<T> toEntity(List<DTO> entities);
}
